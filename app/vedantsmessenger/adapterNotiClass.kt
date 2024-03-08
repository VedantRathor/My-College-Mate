package com.example.android.vedantsmessenger

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterNotiClass(var list : MutableList<Notification>) : RecyclerView.Adapter<adapterNotiClass.vh>() {
    private lateinit var tv : TextView ;

    inner class vh(item: View) : RecyclerView.ViewHolder(item)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_notification, parent, false)
          tv = view.findViewById(R.id.tv_noti)

        return vh(view) ;
    }

    override fun onBindViewHolder(holder: vh, position: Int) {
        holder.itemView.apply {

            tv.setText(list[position].textt.toString())
            tv.setTextColor(resources.getColor(R.color.mycolor9))
        }
    }

    override fun getItemCount(): Int {
      return list.size ;
    }
}