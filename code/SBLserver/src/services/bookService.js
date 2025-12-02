// controller for book related operation trough API
import axios from 'axios';
import {database} from "../models/index.js";
import {Book} from "../models/Book.js";

//constants initialization
const NYTapiKey = process.env.NYT_API_KEY;
const GoogleBooksApiKey = process.env.GOOGLE_BOOKS_API_KEY;
const fictionBest= `https://api.nytimes.com/svc/books/v3/lists/combined-print-and-e-book-fiction.json?published_date=current&api-key=${NYTapiKey}`;
const nonfictionBest= `https://api.nytimes.com/svc/books/v3/lists/combined-print-and-e-book-nonfiction.json?published_date=current&api-key=${NYTapiKey}`;

//simple 'enum' to allow key definitions
export const queryKeys = Object.freeze({
    'isbn': 'isbn',
    'title': 'intitle',
    'author': 'inauthor',
    'genre': 'subject'
})

//saves Db values for bestsellers caching
export let lastUpdated = "";
export let bestFictionCache = [];
export let bestNonFictionCache = [];

/**
 * Insert given book into DB
 * @param {Object} bookInfo generic object containing book info
 * @returns book object promise or null for error
 **/
const saveBookInDB = async (bookInfo) =>{
    try {
        return await database.Book.create({
            genre: bookInfo.categories ? bookInfo.categories.join(', ') : 'Unknown',
            title: bookInfo.title,
            author: bookInfo.authors ? bookInfo.authors.join(', ') : 'Unknown',
            description: bookInfo.description,
            publication_year: new Date(bookInfo.publishedDate).getFullYear() || null,
            isbn: bookInfo.industryIdentifiers[0].identifier,
            cover_url: bookInfo.imageLinks.thumbnail,
        });
    }catch (error){
        console.error('Error creating book in DB', error);
        return null
    }
}

/*==============================ISBN==============================*/
/**
 * Execute a query searching for a book by its registered isbn code, if it doesnt exist fetch from google books api and store it in the db
 * @param {String} isbn registered isbn code
 * @returns book object promise or null for book not found
 **/
export const getBookByIsbnDB = async (isbn) => {
    if(!isbn){
        console.error('No isbn code provided');
        return null;
    }
    try{
        const foundBook = await database.Book.findOne({
            where: {
                isbn: isbn
            }});
        if(!foundBook){
            console.error('DB: No book found with this isbn');
            //fetch from google books api
            const newBook = await getBookByIsbnAPI(isbn);
            //return created book
            return saveBookInDB(newBook);
        }
        //return found book
        return foundBook;
    }catch (err){
        console.error('Error in isbn query:', err)
        return null;
    }
}

/**
 * Execute a query searching for a book by its registered isbn code from goolge books api
 * @param {String} isbn registered isbn code
 * @returns book object promise or null for book not found
 **/
export const getBookByIsbnAPI = async (isbn) => {
    if(!isbn){
        console.error('No isbn code provided');
        return null;
    }
    try{
        const googleISBNAPI= `https://www.googleapis.com/books/v1/volumes?q=isbn:${isbn}&key=${GoogleBooksApiKey}`;
        return await axios.get(googleISBNAPI).then(res => res.data.items[0].volumeInfo);
    }catch (err){
        console.error('Error in api query:', err)
        return null;
    }
}

/**
 * Execute a query searching for a book by the passed params
 * @param {ReqQuery} params the column to search for
 * @returns book object promise or null for book not found
 **/
export const getBookByParam = async (params) => {
    //if params are not given, return error
    if(!params){
        console.error('No params for search query provided');
        return null;
    }


    try{
        //dynamically build where clause
        const whereClause = {
            ...(params.title && { title: params.title }),
            ...(params.author && { author: params.author }),
            ...(params.isbn && {isbn: params}),
            ...(params.genre && { genre: params.genre})
        };
        //scan DB for matching books
        let foundBooks = await database.Book.findAll({where: whereClause });
        //if found any, return the books from DB
        if(foundBooks.length > 0){
            return {opStatus: 200, message: 'success', books: foundBooks};
        }
        //elements for API url
        const apiKey = `&key=${GoogleBooksApiKey}`
        const googleBaseAPI= `https://www.googleapis.com/books/v1/volumes?q=`;
        let query = '';
        //build query dynamically
        for(const key in params){
            for(const queryKey in queryKeys){
                if(key === queryKey){
                    query += `${queryKeys[key]}:${params[key]}+`
                }
            }
        }
        //compose final URL
        const builtURL = googleBaseAPI+query+apiKey
        //fetch data
        foundBooks =  await axios.get(builtURL)
            .then(res => res.data.items);
        const newList = [Book];
        //save or create entity in list
        for(const book of foundBooks){
            newList.push(await getBookByIsbnDB(book.volumeInfo.industryIdentifiers[0].identifier));
        }
        //return object with list
        return {opStatus: 200, message: 'success', books: newList};
    }catch (err){
        console.error('Error in search query:', err)
        return {opStatus: 502, message: err};
    }
}

/**
 * Saves the list of books to the provided destination cache array
 * @param {Array} list list of books from NYT api
 * @param {Array} destination destination cache array
 * @returns {Promise<void>}
 **/
const saveListToCache = async (list, destination) => {
    //clear cache if not empty
    if(destination.length !== 0){
        destination = [];
    }
    //save books of given list
    for (const book of list) {
        //search for existing book first (if not found automatically created)
        const genicDBBook = await getBookByIsbnDB(book.primary_isbn13);
        //if it exists add it to the cache
        if (genicDBBook) {
            destination.push(genicDBBook.dataValues);
        } else {
            console.error(`Book with ISBN ${book.primary_isbn13} could not be found or created in DB.`);
        }
    }
}

//call nyt api for bestsellers
export const NYTBooksFetch = async () => {
    if(!NYTapiKey){
        console.error("NYT API key not set in environment variables.");
        return false;
    }
    try {
        const [fictionResponse, nonfictionResponse] = await Promise.all([
            axios.get(fictionBest).then(res => {
                lastUpdated = res.data.results.bestsellers_date
                return res.data.results.books
            }),
            axios.get(nonfictionBest).then(res => res.data.results.books),
        ]);
        //saving to cache
        await saveListToCache(fictionResponse, bestFictionCache);
        await saveListToCache(nonfictionResponse, bestNonFictionCache);
    } catch (error) {
        console.error("Error fetching NYT Bestseller Lists:", error);
        return false;
    }
    console.log("✅ Initial NYT Books Fetch executed.");
    return true;
}