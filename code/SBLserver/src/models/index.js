//MAIN MODELS ACCESS FILE

//import sequelize instance
import {sequelize} from "../config/dbConfig.js";

//import models in one place for easier export and cleaner code
import {Book} from "./Book.js";
import {BookShelf} from "./Book_shelf.js";
import {Shelf} from "./Shelf.js";
import {User} from "./User.js";
import {UserBook} from "./User_book.js";
import {defineAssociations} from "./association.js";

//objet with all models and sequelize
export const database = {
    sequelize,
    Book,
    BookShelf,
    Shelf,
    User,
    UserBook
};

export const initializeModels = ()=> {
    //define associations --> creating the relationships between tables (from 'association.js')
    defineAssociations(database);
    return database;
}
