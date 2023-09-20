package com.example.android.vedantsmessenger

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ChatsAdapter ( val context : Context , private  val chatlist :ArrayList<chatModal> )
    : RecyclerView.Adapter<ChatsAdapter.chatsviewholder>(){
    class chatsviewholder(view:View):RecyclerView.ViewHolder(view){
        val nname : TextView = view.findViewById(R.id.txtName_chat)
        val email : TextView = view.findViewById(R.id.txtEmail_chat)
        val image : ImageView = view.findViewById(R.id.ContactImage_id_chat)
        val content : CardView = view.findViewById(R.id.usercontent)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatsAdapter.chatsviewholder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_view,parent,false) ;
        return chatsviewholder(view) ;
    }

    override fun onBindViewHolder(holder: ChatsAdapter.chatsviewholder, position: Int) {
        val list  = chatlist[position]
        holder.nname.text = list.receiver
        holder.email.text = list.message
        Picasso.get().load(list.receiverimage).into(holder.image)
        holder.content.setOnClickListener{
            val intent = Intent(context,MenuActivity::class.java).also {


            it.putExtra("OptionName","chatmessaging")
            it.putExtra("chatroom",list.message)
            it.putExtra("receiverName",list.receiver)
            }
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return chatlist.size
    }

}
