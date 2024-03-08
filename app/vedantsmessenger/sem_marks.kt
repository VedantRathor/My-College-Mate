package com.example.android.vedantsmessenger

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class sem_marks : Fragment() {
    private lateinit var tvfragmet : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        var str : String = ""
//        val a = "\n"

//        str = str + "29"

      val view =  inflater.inflate(R.layout.fragment_sem_marks, container, false)

         tvfragmet = view.findViewById(R.id.tvfragment)
        val uid = FirebaseAuth.getInstance().uid
         val myref = FirebaseFirestore.getInstance()
         val list : ArrayList<MutableMap<String,Any>> = arrayListOf()
        var str = ""
        var count = 0 ;
         val ref = myref.collection("students")
             .document(uid!!)
             .collection("semesterMarks")
             .get()
             .addOnSuccessListener {
                 documents ->
                 for( i in documents ){
//                     val doc : String = i.i


//                     str = "$str $a "

                     val t: MutableMap<String, Any>  = i.data

                     var str2 = ""
                     count = count + 1
                     str2 += "sem" + " " + count.toString() + "\n" ;


                 list.add(t)
                     for( i in t ){
                         val fn: String = i.key.toString()
                         val value: Any = i.value.toString()
                        str2 += fn + ":" + value + " " + "\n"
                     }
                     Log.w("-------------", str2)
//                     tvfragmet.setText(str2)

                     str += str2
                     str += "\n\n\n"

                 }
                 tvfragmet.text = str + "\n"
             }

        // fetching the information !!




        return view ;
    }


}