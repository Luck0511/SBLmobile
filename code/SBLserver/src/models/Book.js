//MODEL CLASS FOR BOOK'S DB TABLE

//import sequelize instance
import {sequelize} from "../config/dbConfig.js";

//import data types
import {DataTypes, Model} from "sequelize";

//book table definition
export class Book extends Model {}

Book.init(
    {
    bookID: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true,
    },
    genre: {
        type: DataTypes.STRING(255),
        allowNull: true,
    },
    title: {
        type: DataTypes.STRING(255),
        allowNull: true,
    },
    author: {
        type: DataTypes.STRING(255),
        allowNull: true,
    },
    description: {
        type: DataTypes.STRING(4000),
        allowNull: true,
    },
    publication_year:{
        type: DataTypes.INTEGER,
        allowNull: true,
    },
    isbn:{
        type: DataTypes.STRING(13),
        allowNull: true,
    },
    cover_url:{
        type: DataTypes.STRING,
        allowNull: true,
    },
},
    {
        sequelize,
        modelName: "Book",
        timestamps: false,
    }
);