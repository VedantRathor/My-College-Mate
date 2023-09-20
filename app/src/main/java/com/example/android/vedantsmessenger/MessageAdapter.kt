package com.example.android.vedantsmessenger

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class MessageAdapter( var context : Context , var messageList : ArrayList<MessageModel>):RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    private var left = 0
    private var right = 1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageAdapter.MessageViewHolder {
        return if( viewType == right ){
            val messageView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_message_sender , parent , false  )
            return MessageViewHolder(messageView)
        }else{
            val messageView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_message_receiver , parent , false  )
            return MessageViewHolder(messageView)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if( messageList[position].sender == FirebaseAuth.getInstance().currentUser?.uid.toString()){
            left
        }else{
            right
        }
    }
    override fun onBindViewHolder(holder: MessageAdapter.MessageViewHolder, position: Int) {
            val list = messageList[position]
        holder.message.text = list.message
        holder.time.text = list.timestamp
    }

    override fun getItemCount(): Int {
  return messageList.size ;
    }

    class MessageViewHolder(view : View):RecyclerView.ViewHolder(view){
        val message : TextView = view.findViewById(R.id.txtMessage)
        val time : TextView = view.findViewById(R.id.txtTime)
    }
}
