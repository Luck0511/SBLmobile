//dotenv loader import
import dotenv from 'dotenv';
import {getNumericEnv, getRequiredEnv} from "../utils/envHelpers.js";
dotenv.config();

//application config
export const appConfig = {
    //Application config options --> contains essential app/server settings
    app: {
        name: 'SBL_server',
        env: getRequiredEnv('NODE_ENV'),
        port: getNumericEnv('PORT', 3000),
        host: process.env.HOST || 'localhost',
        url: process.env.APP_URL || `http://localhost:${getNumericEnv('PORT', 3000)}`,
    },

    //Database config options
    database: {
        database: getRequiredEnv('DB_DATABASE'),
        username: getRequiredEnv('DB_USERNAME', 'root'),
        password: getRequiredEnv('DB_PASSWORD'),
        db_uri: process.env.DB_URI || null,
        host: getRequiredEnv('DB_HOST'),
        port: getNumericEnv('DB_PORT', 3306),
        dialect: getRequiredEnv('DB_DIALECT', 'mysql'), //mysql (dev), postgres (prod)
        logging:
        console.log,
        pool: {
            max: getNumericEnv('DB_POOL_MAX', 5),
            min: getNumericEnv('DB_POOL_MIN', 0),
            acquire: getNumericEnv('DB_POOL_ACQUIRE', 30000),
            idle: getNumericEnv('DB_POOL_IDLE', 10000)
        },
        define: {
            timestamps: true, // adds createdAt and updatedAt
            freezeTableName: true, // don't pluralize table names
            underscored: false, //I'm not using snake_case
        }
    },

    //Authentication and security
    auth: {
        jwtSecret: getRequiredEnv('JWT_SECRET'),
        jwtExpires: getRequiredEnv('JWT_EXPIRES_IN'),
        cookieMaxAge: getNumericEnv('COOKIE_MAX_AGE', 86400000), //1 day
        bcryptRounds: getNumericEnv('BCRYPT_ROUNDS', 12)
    }
};