// const Express = require("express") 
// const app = Express() 
//  app.get("/", (req,res)=>{
//     res.send("HOME PAGE")
//  }) 

//  app.get("/about/contact", (req,res)=>{
//    res.send("hello about contact") ;
//  })

//  app.listen(8739, ()=>{
//     console.log("started listening at port num 8739")
//  })

const Express = require("express") ;
const app = Express() ;
// home 
app.get("/",(req,res)=>{
   res.send("<h1>HOME PAGE</h1>") ;
}) 

app.get("/about",(req,res)=>{
   res.send("ABOUT PAGE")
})

app.get("/contact", (req,res)=>{
   res.send("CONTACT PAGE")
})

app.get("/temp" , (req ,res ) =>{
   res.send("TEMP PAGE") 
})

app.listen(8739, ()=>{
   console.log("Started Listening to the prot number")
})