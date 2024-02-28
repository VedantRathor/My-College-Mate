const mongoose = require("mongoose") 
const colors = require("colors")
mongoose.connect("mongodb://localhost:27017/Registraions")
.then( ()=> {
    console.log("connected succesfully".cyan.bgGreen)
} )
.catch((err)=>{
    console.log(err)
})



