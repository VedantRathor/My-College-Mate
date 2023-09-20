package com.example.android.vedantsmessenger

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ContextAdapter(val context : Context , val contactlist : ArrayList<uuser> )
    :RecyclerView.Adapter<ContextAdapter.ContactsViewHolder>() {
    class ContactsViewHolder(view : View):RecyclerView.ViewHolder(view) {
        val nname : TextView = view.findViewById(R.id.txtName)
        val email : TextView = view.findViewById(R.id.txtEmail)
        val status : TextView = view.findViewById(R.id.txtStatus)
        val image : ImageView = view.findViewById(R.id.ContactImage_id)
         val usercontent : CardView = view.findViewById(R.id.usercontent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val contactView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_contacts , parent , false )
        return ContactsViewHolder(contactView)
    }


    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {

        val list : uuser = contactlist[position]
        val s : String = "Name : "
        val s2 : String = "Status : "


        holder.nname.text =  "$s" + list.profileName
        holder.email.text = list.profileEmail
        holder.status.text = "$s2" + list.profileStatus
//        holder.image.text = list.profilePicture
        Picasso.get().load(list.profilePicture).error(R.drawable.add ).into(holder.image)
        holder.usercontent.setOnClickListener{
            val intent = Intent(context,MenuActivity::class.java).also {


                it.putExtra("OptionName","contactMessaging")
                it.putExtra("chatroomID",list.chatRoomId)
                it.putExtra("friendName",list.profileName)

            }
            context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return  contactlist.size
    }






}