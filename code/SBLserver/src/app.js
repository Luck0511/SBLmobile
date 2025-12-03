//utility imports
import express from 'express';
import { createServer } from 'http';
import cors from 'cors';
import cookieParser from 'cookie-parser';
//other imports
import router from './routes/endpoints.js';

export const app = express();
export const server = createServer(app);

//cors options for express (allows cross-origin requests)
const corsOptions = {
    origin: "*"
}

// middleware --> all request and responses pass here before
app.use(cors(corsOptions)); //allow cross-origin request, mainly for localhost testing
app.use(cookieParser()) //parse cookies from requests-response
app.use(express.json()); //parse JSON from requests-response
app.use('/api', router); //router mounting allowing access to API endpoints