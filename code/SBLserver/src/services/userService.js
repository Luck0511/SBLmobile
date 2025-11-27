// controller for player related operation trough API
import {database} from '../models/index.js';
import {passwordHash, verifyPassword} from "./authService.js";

/**
 * Executes basic validation for user data
 * @param {{userName: String, password: String}} userData object containing basic player inputs
 * @param res response reference
 * @returns sets response status code
 **/
export const baseUserValidation = (userData, res) => {
    const {userName, password} = userData;

    if(!userName || !password){
        return res.status(400).json({message: 'Player name and password are required'});
    }
    //check password length
    if(password.length < 6){
        return res.status(400).json({message: 'Password must be at least 8 characters long'});
    }
    //set a maximum length for userName
    if(userName.length > 32){
        return res.status(400).json({message: 'User name must be less than or 32 characters long'});
    }
}

/**
 * Execute a query searching for a user by its registered username
 * @param {String} userName registered user name
 * @returns User object promise or null for user not found
 **/
export const getUserByName = async (userName) => {
    if(!userName){
        console.error('No userName provided');
        return null;
    }
    try{
        const {User} = database;
        return await User.findOne({
            where: {
                username: userName
            }});
    }catch (err){
        console.error('Error in user login:', err)
        return null;
    }
}

/**
 * Register a new user by creating a new row in DB
 * @param {String} userName registered player name
 * @param {String} password registered password
 * @returns {Promise<{opStatus:number, newUser:User?}>} object with final status and nullable Player object
 **/
export const registerNewUser = async (userName, password) => {
    if(!userName || !password){
        return {opStatus: 400}; //{message: 'both userName and password are required'}
    }
    try{
        //check if playerName is already taken
        const existingUser = await getUserByName(userName);
        if(existingUser){
            return {opStatus: 409} //{message: 'An User with this name already exists'};
        }else {
            const {User} = database;
            const hashedPassword = await passwordHash(password)
            const newUser = await User.create({username: userName, password: hashedPassword});
            return {opStatus: 200, newUser: newUser}
            //{message: 'User profile created successfully', userInfo: newUser});
        }
    }catch (err){
        console.error('Error in user registration:', err)
        return {opStatus: 500} //{message: 'Internal server error'};
    }
}

/**
 * Login logic for system, searches existing match in DB and verify password
 * @param {String} userName registered username
 * @param {String} password registered password
 * @returns {Promise<{opStatus: number, loggedUser:User?}>} object with final status and nullable User object
 **/
export const loginUser = async (userName, password) => {
    if(!userName || !password){
        return {opStatus: 400}; //{message: 'userName and password are required'}
    }
    try{
        //search for user by name
        const user = await getUserByName(userName);
        //return error code if user not found
        if(!user){
            return {opStatus: 404}; //{message: 'user not found'};
        }
        //match the passwords
        const isPasswordValid = await verifyPassword(password, user.get('password'));
        //return code 200 success if password matches
        if(isPasswordValid){
            return {opStatus: 200, loggedUser: user}
            //{message: 'user found, credentials correct, logging into account'}
        }else{
            return {opStatus: 401}; //{message: 'Wrong password'};
        }
    }catch(err){
        console.error('Error in user login:', err)
        return {opStatus: 500} //{message: 'Internal server error'};
    }
}