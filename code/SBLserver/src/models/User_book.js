//JUNCTION DB TABLE FOR 'USER' AND 'BOOK'

//import sequelize instance
import {sequelize} from "../config/dbConfig.js";

//import data types
import {DataTypes, Model} from "sequelize";

//user_book junction definition
export class UserBook extends Model {}

UserBook.init(
    {
        userID:{
            type: DataTypes.INTEGER,
            primaryKey: true,
            allowNull: false,
        },
        bookID:{
            type: DataTypes.INTEGER,
            primaryKey: true,
            allowNull: false,
        },
        times_read:{
            type: DataTypes.INTEGER,
            allowNull: true,
            defaultValue: 0,
        },
        last_read_at:{
            type: DataTypes.DATE,
            allowNull: true,
        },
},
    {
        sequelize,
        modelName: "user_book",
        timestamps: false,
    })