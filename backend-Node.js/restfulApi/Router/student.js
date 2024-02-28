
const express = require("express")
const app = express() 
const router = new express.Router() 
const PATH = "C:/Users/vedant.rathore/Desktop/ExpressJS/restfulApi/Models/students"
const Student = require(PATH)

router.get("/",(req,res)=>{
    res.send("<h1>HOME</h1>")
})

router.get("/about",(req,res) =>{k
    res.send("<h1>ABOUT</h1>")
})

router.get("/contact",(req,res) =>{
    res.send("<h1>CONTACT</h1>")
})

router.use(express.json())
router.post("/students", async(req,res) => {
     try{
         const docuser = new Student(req.body)
         const createUser = await docuser.save() 
         res.status(201).send("User Succesfully inserted")
     }catch{
        (err) => res.status(401).send(err)
     }
})

router.get("/students",async (req,res) => {
    
    try{
         
         const docStudents = await Student.find()
         res.send(docStudents)
    }catch{
        (err) => { console.log(err) } 
    }
})

router.get("/students/:id" , async(req,res) =>{
     try{const _id = req.params.id
     const getData = await Student.findById({_id:_id})
     console.log(getData)
     res.send(getData)
    }
    catch{
        (err) => { console.log(err)}
    }
})

router.patch("/students/:id" , async(req,res) => {

    try{
        const _id = req.params.id 
        const updateRes = await Student.findByIdAndUpdate({_id:_id},req.body,{new:true})
        console.log(updateRes) 
        res.send(updateRes )
    }catch{
        (err) => {
            console.log(err)
        }
    }
     
})

router.delete("/students/:id", async(req,res) =>{ 
   
       try{
       
           const getId = req.params.id 
           const delRes = await Student.deleteMany({_id:getId})
           res.send(delRes)
          
       }catch{
        (err) => {
            console.log(err)
        }
       }
} )

module.exports = router