//utility imports
import express from "express";

//services imports
import {baseUserValidation, loginUser, registerNewUser} from "../services/userService.js";
import {bestFictionCache, bestNonFictionCache, getBookByParam, lastUpdated} from "../services/bookService.js";

//set up a router to manage all endpoints
const router = express.Router();

//here all the api definitions

/**
 * #### Endpoint for server status check
 * GET /api/health
 *
 * Responses:
 * - 200: Server is up and running
 **/
router.get("/health" , (req, res) => {
    res.json({
        status: "Server is up and running"
    });
})


/**
 * #### Endpoint to register a new user
 * POST /api/register
 *
 * Request Body:
 * - userName: string (required) - The desired username for the new user.
 * - password: string (required) - The password for the new user.
 *
 * Responses:
 * - 200 : User profile created successfully
 * - 400 : User name and password are required
 * - 409 : An User with this name already exists
 * - 500 : There has been an internal server error
 * - 520 : Generic unknown error
 **/
router.post('/register', async (req, res) => {
    const {userName, password} = req.body;

    //execute base validation on name and password
    baseUserValidation({userName, password}, res);

    //user creation and returns status code, if success, also returns new player object
    const {opStatus, newUser} = await registerNewUser(userName, password);

    //status code switch
    switch(opStatus){
        case 200: {
            return res.status(200).json({
                message: 'User profile created successfully',
                userInfo: {
                    id: newUser.get('userID'),
                    userName: newUser.get('username'),
                }
            });
        }
        case 400: {
            return res.status(400).json({message: 'User name and password are required'});
        }
        case 409: {
            return res.status(409).json({message: 'An User with this name already exists'});
        }
        case 500: {
            return res.status(500).json({message: 'There has been an internal server error'});
        }
        default: {
            return res.status(520).json({message: 'Generic unknown error'});
        }
    }
})

/**
 * #### Endpoint for user login
 * POST /api/login
 *
 * Request Body:
 * - userName: string (required) - The desired username for user.
 * - password: string (required) - The password for user.
 *
 * Responses:
 * - 200 : User logged in successfully
 * - 400 : User name and password are required
 * - 401 : Wrong password
 * - 404 : User not found
 * - 500 : There has been an internal server error
 * - 520 : Generic unknown error
 **/
router.post('/login',async (req, res) => {
    const {userName, password} = req.body;

    //execute base validation on name and password
    baseUserValidation({userName, password}, res);

    //player search and returns status code, if success, also returns logged player object
    const {opStatus, loggedUser} = await loginUser(userName, password);

    //status code switch
    switch (opStatus) {
        case 200: {
            return res.status(200).json({
                message: 'User logged in successfully',
                userInfo: {
                    id: loggedUser.userID,
                    userName: loggedUser.username,
                }
            });
        }
        case 400: {
            return res.status(400).json({message: 'User name and password are required'});
        }
        case 401: {
            return res.status(401).json({message: 'Wrong password'});
        }
        case 404: {
            return res.status(404).json({message: 'User not found'});
        }
        case 500: {
            return res.status(500).json({message: 'There has been an internal server error'});
        }
        default: {
            return res.status(520).json({message: 'Generic unknown error'});
        }
    }
})

/**
 * #### Endpoint that returns cached books
 * GET /api/getTrending
 *
 * Responses:
 * - 200 : object containing: bestseller_date, fiction, nonFiction, unifiedList of books
 **/
router.get('/getTrending', (req, res) => {
    res.status(200).json({
        trending: {
            bestsellers_date: lastUpdated,
            fiction: bestFictionCache,
            nonfiction: bestNonFictionCache,
            unifiedList: [...bestFictionCache, ...bestNonFictionCache],
        }
    })
})

/**
 * #### Endpoint to return a specific book
 * GET /api/searchBook
 *
 * Query Params:
 * - query: the input of the search bar
 *
 * Responses:
 * - 200 : object containing the books matching the query
 **/
router.get('/searchBook', async (req, res) => {
    const queries = req.query;
    try{
        const status = await getBookByParam(queries);
        res.status(status.opStatus).json({
            ...(status.message && { message: status.message }),
            ...(status.books && { books: status.books })
        })
    }catch(err){
        console.error(err);
        res.status(500).json({message: 'There was an internal server error'});
    }
})

//export the router to be used in app.js
export default router;