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
    private lateinit var chatREcyclerView: RecyclerView
    private lateinit var chatlayout_manager: RecyclerView.LayoutManager
    private lateinit var chatsadapter: ChatsAdapter
    private lateinit var fstore: FirebaseFirestore

    //private lateinit var db : DocumentReference
    private lateinit var auth: FirebaseAuth

    // fetching the adapter
    private val chatsinfo = arrayListOf<chatModal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_chats, container, false)


        fstore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
//        contactREcyclerView.adapter = contact_adapter

//        db = fstore.collection("users").document()


       chatREcyclerView = view.findViewById(R.id.chatsRecyclerView)
        chatlayout_manager = LinearLayoutManager(context as Activity)
        fstore.collection("chats").whereArrayContains("uids",auth.currentUser!!.uid).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.d("", "")
            } else{
                if (!snapshot?.isEmpty!!) {
                    chatsinfo.clear()
                    val list = snapshot.documents
                    for (i in list) {
                        fstore.collection("chats").document(i.id).collection("message")
                            .orderBy("messageId",Query.Direction.DESCENDING)
                            .addSnapshotListener { messagesnapshot, exception ->
                            if( snapshot != null ){
                                Log.d("error","error hai")
                        } else{
                            val id = messagesnapshot!!.documents[0]
                                val message = id.get("message").toString()
                                val receiver = id.get("receiver").toString()
                                val ccontact = chatModal(receiver,message,"https://firebasestorage.googleapis.com/v0/b/vedant-s-messenger.appspot.com/o/moV8Mqy5lRRO6kNatr9i1sHFUti1%2FprofilePhoto?alt=media&token=3640377f-5209-417f-b4dd-3af5f49b0723",i.id)
                                chatsinfo.add(ccontact)
                            }
//                        if (i.id == auth.currentUser?.uid) {
//                            Log.d("onFound", "this is user's account so no display")
//                        } else {
//                            // fetching the data
//                            val ccontact = uuser(
//                                i.getString("userName").toString(),
//                                i.getString("userEmail").toString(),
//                                i.getString("userStatus").toString(),
//                                i.getString("userProfilePhoto").toString()
//                            )


                            chatsadapter = ChatsAdapter(context as Activity, chatsinfo)
                            chatREcyclerView.adapter = chatsadapter
                            chatREcyclerView.layoutManager = chatlayout_manager
                            chatREcyclerView.addItemDecoration(
                                DividerItemDecoration(
                                    chatREcyclerView.context,
                                    (chatlayout_manager as LinearLayoutManager).orientation
                                )
                            )


                        }

                    }
                }
        }
        }

        return view ;
    }


}