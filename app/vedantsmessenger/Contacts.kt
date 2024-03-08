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
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class Contacts : Fragment() {

    private lateinit var contactREcyclerView: RecyclerView
    private lateinit var cont_layout_manager: RecyclerView.LayoutManager
    private lateinit var contact_adapter: ContextAdapter
    private lateinit var fstore: FirebaseFirestore
    private lateinit var userid: String

    //private lateinit var db : DocumentReference
    private lateinit var auth: FirebaseAuth

    // fetching the adapter
    private val contactainfo = arrayListOf<uuser>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_contacts, container, false)


        fstore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        userid = auth.currentUser!!.uid.toString()
//        contactREcyclerView.adapter = contact_adapter

//        db = fstore.collection("users").document()


        contactREcyclerView = view.findViewById(R.id.fragment_contact_id)
        cont_layout_manager = LinearLayoutManager(context as Activity)
        fstore.collection("users")
            .document(userid)
            .collection("friends")
            .get().addOnSuccessListener {
            if (!it.isEmpty) {
                contactainfo.clear()
                val listcontact = it.documents
                for (i in listcontact) {
                    val friendID = i.id
                    val chatRoomID = i.getString("chatroomid")

//                    if (i.id == auth.currentUser?.uid) {
//                           Log.d("onFound" , "this is user's account so no display")
//                    } else {
                        // fetching the data
                    fstore.collection("users").document(friendID).addSnapshotListener { value, error ->
                        if(error != null ){
                        Log.d("","")}
                        else{
                            val ccontact = uuser(
                                value!!.getString("userName").toString(),
                                value.getString("userEmail").toString(),
                                value.getString("userStatus").toString(),
                                value.getString("userProfilePhoto").toString(),
                                chatRoomID.toString()
                            )
                            contactainfo.add(ccontact)

                            contact_adapter = ContextAdapter(context as Activity, contactainfo)
                            contactREcyclerView.adapter = contact_adapter
                            contactREcyclerView.layoutManager = cont_layout_manager
                            contactREcyclerView.addItemDecoration(
                                DividerItemDecoration(
                                    contactREcyclerView.context,
                                    (cont_layout_manager as LinearLayoutManager).orientation
                                )
                            )
                        }
                    }





                }
            }
        }

//        contactainfo.add(ccontact)
//
//        contact_adapter = ContextAdapter(context as Activity, contactainfo)
//        contactREcyclerView.adapter = contact_adapter
//        contactREcyclerView.layoutManager = cont_layout_manager
//        contactREcyclerView.addItemDecoration(
//            DividerItemDecoration(
//                contactREcyclerView.context,
//                (cont_layout_manager as LinearLayoutManager).orientation
//            )
//        )




        return view;
    }


}