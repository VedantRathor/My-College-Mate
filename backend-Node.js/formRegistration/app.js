require('dotenv').config()
console.log(process.env) // remove this after you've confirmed it is working

console.log("hey in form registration!")
const express = require("express")
const app = express()
const colors = require("colors")
const mongoose = require("mongoose")
const path = require("path")
const PORT = process.env.PORT || 4000
const hbs = require("hbs")
const bcrypt = require("bcryptjs")
const jwt = require("jsonwebtoken") 


/* require */

/* settings for the hbs file ! */
app.set("view engine", "hbs")
app.set("views", "C:/Users/vedant.rathore/Desktop/ExpressJS/formRegistration/templates/views")
// app.use(express.static("C:/Users/vedant.rathore/Desktop/ExpressJS/formRegistration/templates/views/index.hbs"))
require("C:/Users/vedant.rathore/Desktop/ExpressJS/formRegistration/connection.js")
const users = require("C:/Users/vedant.rathore/Desktop/ExpressJS/formRegistration/Models/userRegistraions.js")
const { log } = require("console")

const preventCache = (req, res, next) => {
    res.header('Cache-Control', 'private, no-cache, no-store, must-revalidate');
    res.header('Expires', '-1');
    res.header('Pragma', 'no-cache');
    next();
};

app.get("", preventCache , (req, res) => {
    res.render("C:/Users/vedant.rathore/Desktop/ExpressJS/formRegistration/templates/views/index.hbs")
})



app.get("/about", (req, res) => {
    res.send("ABOUT")
})

app.get("/contact", (req, res) => {
    res.send("CONTACT")
})


app.use(express.json())
app.use(express.urlencoded({ extended: true }))
app.post("/register", async (req, res) => {
    try {

        const fname = req.body.firstname
        const lname = req.body.lastname
        const username = req.body.username
        const city = req.body.city
        const zip = req.body.zip
        const password = req.body.password
        const cpassword = req.body.cpassword
                 const finduser = await users.findOne({username:username}) 
                 if( finduser != null ){
                        res.render("C:/Users/vedant.rathore/Desktop/ExpressJS/formRegistration/templates/views/alreadyExists.hbs")
                 }else{  
                    if (password === cpassword) {
                        // register this user! 
            
                        const doc1 = new users({
                            fname: fname,
                            lname: lname,
                            username: username,
                            city: city,
                            zip: zip,
                            password: password
                        })
            
                        // we'll do the hasing over here! using the bcrypt! 
                        // now we will create a JSONwebtoken! 
                        const token = await doc1.generateToken() 
                        console.log(token)
                        const result = await doc1.save()
                        console.log(result)
                        res.render("C:/Users/vedant.rathore/Desktop/ExpressJS/formRegistration/templates/views/loginPage.hbs")
                    } else {
                        console.log("passwords are not matching")
                    }
                   }
       
    } catch (err) {
        console.log(err)
    }


})

app.post("/login",  async (req, res) => {
    try {
        const username = req.body.username
        const password = req.body.password
        const result = await users.findOne({ username: username })

        if (result != null) {
            const isSame = await bcrypt.compare(password, result.password)
            if (isSame) {
                const token = await result.generateToken() 
                console.log(token)
                res.render("C:/Users/vedant.rathore/Desktop/ExpressJS/formRegistration/templates/views/loginPage.hbs")
            }else{
                res.render("C:/Users/vedant.rathore/Desktop/ExpressJS/formRegistration/templates/views/errorpage.hbs")
            }
        } else {
            res.render("C:/Users/vedant.rathore/Desktop/ExpressJS/formRegistration/templates/views/errorpage.hbs")
        }
    } catch (error) {
        console.log(error)
    }
})


// const createToken = async (_id) => {
//          try {
//            const  token = await jwt.sign({_id} , "mynameisvedantrathoreandiamthebest")
//            console.log(token)
//          } catch (error) {
//             console.log(err)
//          }
// }

// createToken("65dc61e4b4e391b88f435f8f")

app.listen(PORT, () => {
    console.log("Started listening at your port number".cyan.bgGreen)
})