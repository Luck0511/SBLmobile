//MODEL CLASS FOR USER'S DB TABLE

//import sequelize instance
import {sequelize} from "../config/dbConfig.js";

//import data types
import {DataTypes, Model} from "sequelize";

//user table definition
export class User extends Model {
}

User.init(
    {
        userID: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
            allowNull: false
        },
        username: {
            type: DataTypes.STRING(32),
            allowNull: false,
            unique: true
        },
        password: {
            type: DataTypes.STRING(255),
            allowNull: false
        },
        img_url: {
            type: DataTypes.STRING
        },
        is_active: {
            type: DataTypes.BOOLEAN,
            allowNull: false
        },
        created_at: {
            type: DataTypes.DATE
        }
    },
    {
        sequelize,
        tableName: "user",
        createdAt: "created_at",
        updatedAt: false
    }
);
