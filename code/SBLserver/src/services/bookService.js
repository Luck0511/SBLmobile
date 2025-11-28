// controller for book related operation trough API
import axios from 'axios';
import {database} from "../models/index.js";

//constants initialization
const NYTapiKey = process.env.NYT_API_KEY;
const GoogleBooksApiKey = process.env.GOOGLE_BOOKS_API_KEY;
const fictionBest= `https://api.nytimes.com/svc/books/v3/lists/combined-print-and-e-book-fiction.json?published_date=current&api-key=${NYTapiKey}`;
const nonfictionBest= `https://api.nytimes.com/svc/books/v3/lists/combined-print-and-e-book-nonfiction.json?published_date=current&api-key=${NYTapiKey}`;

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
            return await database.Book.create({
                genre: newBook.categories ? newBook.categories.join(', ') : 'Unknown',
                title: newBook.title,
                author: newBook.authors ? newBook.authors.join(', ') : 'Unknown',
                description: newBook.description,
                publication_year: new Date(newBook.publishedDate).getFullYear() || null,
                isbn: newBook.industryIdentifiers[0].identifier,
                cover_url: newBook.imageLinks.thumbnail,
            });
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

//call nyt api for bestsellers
export const initialNYTBooksFetch = async () => {
    if(!NYTapiKey){
        console.error("NYT API key not set in environment variables.");
        return false;
    }

    try {
        const [fictionResponse, nonfictionResponse] = await Promise.all([
            axios.get(fictionBest).then(res => res.data.results.books),
            axios.get(nonfictionBest).then(res => res.data.results.books),
        ]);
        //merging both lists
        const allBooks = [...fictionResponse, ...nonfictionResponse];
        //iterating over all books
        for (const nytData of allBooks) {
            //this allows to search in db and if not found fetch from google books api and store it in the db
            await getBookByIsbnDB(nytData.primary_isbn13);
        }

    } catch (error) {
        console.error("Error fetching NYT Bestseller Lists:", error);
        return false;
    }
    console.log("✅ Initial NYT Books Fetch executed.");
    return true;
}