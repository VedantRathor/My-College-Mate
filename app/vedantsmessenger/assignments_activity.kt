package com.example.android.vedantsmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class assignments_activity : AppCompatActivity() {
    private  lateinit var adapter : adp_ass
    private  lateinit  var list2 : ArrayList<dc>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignments)
        val rv : RecyclerView = findViewById(R.id.rvassignments)
        rv.layoutManager = LinearLayoutManager(this)


        val uid = FirebaseAuth.getInstance().uid
        list2 = arrayListOf()
        adapter = adp_ass(list2)
        rv.adapter = adapter

        var list: MutableMap<String, Any> = mutableMapOf<String,Any>()
             FirebaseFirestore.getInstance().collection("assignments")
                 .document(uid!!)
                 .get()
                 .addOnSuccessListener {
                     documents ->

                      if( documents != null ){
                         val t: MutableMap<String, Any>? = documents.data

                          if (t != null) {

//                            Log.d("-----------------",t.toString())
                              for( it in t ){
                                  val s1 = it.key.toString()
                                  val s2 = it.value.toString()
//                                  Log.d("--------","$s1 + $s2")
                                  list2.add(dc(s1+" "+s2))
                                  adapter.notifyDataSetChanged()
                              }
                            for( it in list2 ){
                                Log.d("------------------","${it.str}")
                            }

                          }


                      }

                 }
        // list is ready!



    }
}