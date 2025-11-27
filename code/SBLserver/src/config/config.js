//dotenv loader import
import dotenv from 'dotenv';
dotenv.config();

//application config
export const appConfig = {
    //Application config options --> contains essential app/server settings
    app: {
        name: 'SBL_server',
        env: process.env.NODE_ENV || 'development',
        port: process.env.PORT || 3000,
        host: process.env.HOST || 'localhost',
        url: process.env.APP_URL || `http://localhost:${process.env.PORT || 3000}`,
    },

    //Database config options/setting
    database: {
        database: process.env.DB_DATABASE || 'sbl_db',
        username: process.env.DB_USERNAME || 'root',
        password: process.env.DB_PASSWORD || '1234',
        db_uri: process.env.DB_URI || null,
        host: process.env.HOST || 'localhost',
        port: process.env.DB_PORT || 3306,
        dialect: process.env.DB_DIALECT || 'mysql', //mysql (dev and prod?)
        logging:
        console.log,
        pool: {
            acquire: process.env.DB_POOL_ACCURRENCY || 30000,
            idle: process.env.DB_POOL_IDLE || 30000
        },
        define: {
            timestamps: true, // adds createdAt and updatedAt
            freezeTableName: true, // don't pluralize table names
            underscored: false, //I'm not using snake_case
        }
    },

    //Authentication and security
    auth: {
        jwtSecret: process.env.JWT_SECRET,
        jwtExpires: process.env.JWT_EXPIRES_IN || '7d',
        cookieMaxAge: process.env.COOKIE_MAX_AGE || 604800000, //1 day
        bcryptRounds: process.env.BCRYPT_ROUNDS || 12,
    }
};