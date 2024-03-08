package com.example.android.vedantsmessenger

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import java.util.Calendar


class messaging : Fragment() {
       private lateinit var message_rv : RecyclerView
    private lateinit var sendMessage_et : EditText
    private lateinit var sendMessage_btn : FloatingActionButton
    private lateinit var fstore: FirebaseFirestore
    private lateinit var fauth : FirebaseAuth
    private lateinit var msglayoutManager : RecyclerView.LayoutManager
    private lateinit var msgAdapter : MessageAdapter
    private lateinit var db : DocumentReference
    private lateinit var db1 : DocumentReference
    private lateinit var userid : String
    private lateinit var friendID: String
    private lateinit var chatroomID : String
    private lateinit var chatID : String
    private lateinit var chatroomuserID : String
    private lateinit var register : ListenerRegistration
    private lateinit var register1 : ListenerRegistration
    private  var messageInfo = arrayListOf<MessageModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View  = inflater.inflate(R.layout.fragment_messaging, container, false)
        message_rv = view.findViewById(R.id.messagerv)
        sendMessage_btn = view.findViewById(R.id.floatingactionbtn)
        sendMessage_et = view.findViewById(R.id.etsendmessage)


        val values = arguments
        if( values != null ){
            friendID= values!!.getString("friendName").toString()
            chatroomID = values.getString("documentID").toString()
            initialization(chatroomID)


        }

        val contactBundle = arguments
        if( contactBundle != null ){
          friendID = values!!.getString("friendName").toString()
            chatroomID = values.getString("chatRoomID").toString()
            Log.d("logContactbundle",friendID)
            Log.d("logContactbundle",chatroomID)
            fetchChatRoomUID()
//            initialization()
//            recyclerViewBuild()
//            fetchMessageID()
//            fetchmessages()
        }
        sendMessage_btn.setOnClickListener {
            fetchMessageID()
        }

        return view ;
    }

    private fun fetchChatRoomUID(){
        fstore.collection("chats").whereEqualTo("chatroomid",chatroomID)
            .get().addOnSuccessListener {
                query->
                if( !query.isEmpty){
                    chatroomuserID = query.documents[0].id
                    initialization(chatroomuserID)
//                    recyclerViewBuild()
//                    fetchMessageID()
//                    fetchmessages()
                }
            }
    }

    private fun fetchmessages(idmsg:String) {
         register1 = fstore.collection("chats")
            .document(idmsg)
            .collection("message")
            .orderBy("messageId",Query.Direction.ASCENDING).addSnapshotListener{
                chatSnapshot,exception ->
                if( exception != null ){
                    Log.d("","")
                }else{
                    if( !chatSnapshot?.isEmpty!!){
                        val listchat = chatSnapshot.documents
                        for( chat in listchat){
                                       val chatobj = MessageModel(
                                           chat.getString("messageSender").toString(),
                                           chat.getString("message").toString(),
                                           chat.getString("messageTime").toString()
                                       )
                            messageInfo.add(chatobj)
                        }
                        message_rv.scrollToPosition(chatSnapshot.size()-1)
                        msgAdapter.notifyDataSetChanged()
                    }
                }
            }
    }

    private fun fetchMessageID() {
        db = fstore.collection("chats").document(chatroomuserID).collection("count").document()
        sendMessage_btn.setOnClickListener{
            register= db.addSnapshotListener { value, error ->
                if( error != null ){
                    Log.d("","")
                }else{
                    chatID = value?.getString("chatid").toString()

                    sendMessage()

                }
            }
        }
    }

    private fun recyclerViewBuild(id:String) {
         msgAdapter = MessageAdapter(context as Activity  , messageInfo)
        message_rv.adapter = msgAdapter
        message_rv.layoutManager = msglayoutManager
        fetchmessages(id)


    }

    private fun initialization( id : String) {
        fstore = FirebaseFirestore.getInstance()
        fauth = FirebaseAuth.getInstance()
        userid = fauth.currentUser?.uid.toString()
        msglayoutManager = LinearLayoutManager(context)
        db1 = fstore.collection("chats").document(id).collection("message")
            .document()
        recyclerViewBuild(id)


    }

    private fun sendMessage() {
        register!!.remove()
         val msg = sendMessage_et.text.toString()
        if( TextUtils.isEmpty(msg)){
            sendMessage_et.error = "Enter some Message to Send"
        }else{
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val mins = c.get(Calendar.MINUTE)
            val timeStamp = "$hour:$mins"
            val msgobj = mutableMapOf<String,Any>().also {
                it["message"] = msg
                it["messageSender"] = userid
                it["messageId"] = chatID
                it["messageReceiver"] = friendID
                it["messageTime"] = timeStamp


            }
            db1.set(msgobj).addOnSuccessListener {
                Log.d("onSucces","Succesfully sned message")
            }
            val countid = mutableMapOf<String,String>()
            countid["chatid"] = (chatID.toInt()+1).toString()
            db.set(countid).addOnSuccessListener {
                Log.d("","")
            }

        }
    }

    override fun onDestroy() {
        register!!.remove()
        super.onDestroy()
    }
}