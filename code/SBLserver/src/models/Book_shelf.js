//JUNCTION DB TABLE FOR 'BOOK' AND 'SHELF'

//import sequelize instance
import {sequelize} from "../config/dbConfig.js";

//import data types
import {DataTypes, Model} from "sequelize";

//book_shelf junction definition
export class BookShelf extends Model {
}

BookShelf.init(
    {
        bookID: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            allowNull: false,
        },
        shelfID: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            allowNull: false,
        },
        added_at: {
            type: DataTypes.DATE,
        },
        is_read: {
            type: DataTypes.BOOLEAN,
            defaultValue: false,
        },
    },
    {
        sequelize,
        tableName: "book_shelf",
        createdAt: "added_at",
        updatedAt: false
    });
