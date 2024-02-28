require('dotenv').config()
const mongoose = require("mongoose")
const bcrypt = require("bcryptjs")
const jwt = require("jsonwebtoken")

const userSchema = new mongoose.Schema({
    fname: String,
    lname: String,
    username: String,
    city: String,
    zip: Number,
    password: String,
    tokens: [{
        token: {
            type: String
        }
    }]
})

userSchema.methods.generateToken = async function () {
    try {

        const id = this._id.toString();

        const token = await jwt.sign({ id }, process.env.SECRET_KEY)
        this.tokens.push({ token })
        // this.save()
        return token
    } catch (error) {
        throw error
        console.log(error)
    }
}

userSchema.pre("save", async function (next) {
    try {
        if (this.isModified("password")) {
            this.password = await bcrypt.hash(this.password, 10)

        }

        next();  // Call next to move to the next middleware
    } catch (error) {
        console.log(error)
        next(error) // Pass the error to move to the error handling middleware
    }
})

const users = new mongoose.model("User", userSchema)
module.exports = users 