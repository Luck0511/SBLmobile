//utility imports
import express from "express";

//set up a router to manage all endpoints
const router = express.Router();

//here all the api definitions
router.get("/getusername", (req, res) => {
    res.json({
        username: "PlayerOne"
    });
})

//export the router to be used in app.js
export default router;