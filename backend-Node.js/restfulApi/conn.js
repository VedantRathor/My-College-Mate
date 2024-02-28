// connect with mongodb
const mongoose = require("mongoose") 
mongoose.connect("mongodb://localhost:27017/Users")
.then( ()=>{
    console.log("Connection succesfull")
} )
.catch( (err)=>{console.log(err)})