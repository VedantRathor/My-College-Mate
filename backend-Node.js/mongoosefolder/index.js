console.log("hey Iam in mongoose!! ")
// require modules! 
const mongoose = require("mongoose");
//connection establishment!
mongoose.connect("mongodb://localhost:27017/vedantDB")
    .then(() => { console.log("connection succesful....") })
    .catch(
        (err) => {
            console.log(err)
        }
    )

// defining the structure of a schema! 
const employeeSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    age: Number,
    Company: String,
    Contact: String,
    date: {
        type: Date,
        default: Date.now
    }
})

// model!! + collection  
const Employee = new mongoose.model("Employee", employeeSchema)

// inserting a single doucmnet! 

/*
    const insertOneDoc = async() => {
        try{
            const doc_one = Employee({
                name : "Vedant Rathore",
                age : 22 ,
                Company: "Helios Solutions",
                Contact: "9131403200"
            
            })
            const result = await doc_one.save()
            console.log(result)
        }catch{
            (err) => {
            console.log(err) 
            }
        }
    }

    insertOneDoc()
*/

// inserting multiple documents ! 

// const insertManyDocs = async () => {
//     try {
//         const doc1 = Employee(
//             {
//                 name: "Ram",
//                 age: 22,
//                 Company: "XksOOWW",
//                 Contact: "913XXXXXXX"
//             }
//         )

//         const doc2 = Employee({
//             name: "Shyam",
//             age: 22,
//             Company: "Student",
//             Contact: "71218XXXXX"
//         })

//         const doc3 = Employee({
//             name: "Radha",
//             age: 22,
//             Company: "Student",
//             Contact : "70003XXXXX"
//         })

//         const res = await Employee.insertMany([doc1,doc2,doc3]) 
//         console.log(res) 

//     } catch {
//         (err) => {
//             console.log(err)
//         }
//     }
// }

// insertManyDocs()


// const getAllDocs = async() => { 
//        try{ 
//           // backgrounfd threadD!! ! ! 
//           const RES = await Employee.find()
//           console.log(RES)
//        }catch {
//         (err) => {
//           console.log(err) 
//         }
//        }
// }


// getAllDocs()


// const getAllDocsCompany = async() => {
//     try{
//          const res = await Employee.find({Company:"Student"}).select({name:1,age:1,_id:0})
//          console.log(res) 
//     }catch {
//         (err) => {
//             console.log(err)
//         }
//     }
// }
// getAllDocsCompany()


// const getAllDocs = async() => {
//     try{
//           const res = await Employee.find({$or : [ {$and:[{age:{$gte:20}},{age:{$lte:30}}] } , {age:{$gte:35}} ]} ).select({name:1,age:1,_id:0})
//           console.log(res) 
//     }catch{
//         (err) => {
//             console.log(err)
//         }
//     }
// }
// getAllDocs()


// count the number of docoumnets having the age >= 20 and age <= 30 OR age > 35 
// const getAllDocs = async() => {
//     try{
//           const res = await Employee.find({Company:"Student"}).sort({name:1})
//         //   console.log(res) 
//     }catch{
//         (err) => {
//             console.log(err)
//         }
//     }
// }
// getAllDocs()

// const updateOneDoc = async(id) => {
//     try{
//          const result = await Employee.findByIdAndUpdate({_id:id}, {$set:{name:"Radha JIIIIIII" } } , { new : true} ) 
//          console.log(result)
//     }catch{
//         (err) => {
//             console.log(err)
//         }
//     }
// }
// updateOneDoc("65d6da343083c354e2ab54a1")


/* ------ Deleting a document by deleteOne/findByIdAndDelete({<filter>})*/

const deleteDoc = async(id) => {
    try{
         
          const res = await Employee.deleteOne({_id:id}) 
          console.log(res) 
    }catch{
        (err) => {
            console.log(err)
            
        }
    }
}



deleteDoc("65d6da343083c354e2ab549f")