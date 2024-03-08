package com.example.android.vedantsmessenger

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Chats : Fragment() {
  private lateinit var rv_noti : RecyclerView
  private lateinit var noti_list : MutableList<Notification>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_chats, container, false)
           rv_noti = view.findViewById(R.id.chatsRecyclerView)
          rv_noti.layoutManager = LinearLayoutManager(this.context)
          noti_list = mutableListOf()

        for( i in 1..10){
            val t : String = "your assignment $i is pending" ;
            noti_list.add(
                Notification(t)
            )
        }

        for( i in 11..17){
            val t : String = "your assignment $i is submitted" ;
            noti_list.add(
                Notification(t)
            )
        }
        val adapter : adapterNotiClass =adapterNotiClass(noti_list)
        rv_noti.adapter = adapter ;
        return view ;
    }


}