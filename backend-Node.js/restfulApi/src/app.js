console.log("Hey!!") ; 
/* require all the stuff!  */
const express = require("express") 
const app = express() 
const validator = require("validator")
const mongoose = require("mongoose") 
const PORT = process.env.PORT || 4000
const path = require("path");
require("C:/Users/vedant.rathore/Desktop/ExpressJS/restfulApi/conn.js")
const myPath = path.join(__dirname,"../Models/students") 
const Student = require(myPath)

const studentrouter = require("C:/Users/vedant.rathore/Desktop/ExpressJS/restfulApi/Router/student.js")
app.use(studentrouter)

// const { get } = require("http");

// const Student = require('./')



app.listen(PORT,()=>{
    console.log(`started listening at ${PORT}`)
})