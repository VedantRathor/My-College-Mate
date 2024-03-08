package com.example.android.vedantsmessenger


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

class adapterEventList(val context: Activity, val list: MutableList<String> ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myVH {
        val view = LayoutInflater.from(context).inflate(R.layout.single_item_eventsrv, parent, false)
        val viewholder = myVH(view)
        return viewholder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val str = list[position]
        (holder as myVH ).bindData(str)
    }

    inner class myVH(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val tv: TextView

        init {
            tv = itemview.findViewById(R.id.tv_events)
        }

        fun bindData(str: String) {
           tv.text = str
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}