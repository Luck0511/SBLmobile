//MODEL CLASS FOR SHELF'S DB TABLE

//import sequelize instance
import {sequelize} from "../config/dbConfig.js";

//import data types
import {DataTypes, Model} from "sequelize";

//shelf table definition
export class Shelf extends Model {
}

Shelf.init(
    {
        shelfID: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
            allowNull: false
        },
        userID: {
            type: DataTypes.INTEGER,
            allowNull: false
        },
        name_shelf: {
            type: DataTypes.STRING(32),
            allowNull: false
        },
        is_default: {
            type: DataTypes.BOOLEAN,
            allowNull: false,
            defaultValue: false
        },
        is_active: {
            type: DataTypes.BOOLEAN,
            allowNull: false,
            defaultValue: true
        }
    },
    {
        sequelize,
        tableName: "shelf",
        timestamps: false,
    }
);