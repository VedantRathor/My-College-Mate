package com.example.android.vedantsmessenger

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

class adapterEvent(val context: Activity, val list: MutableList<Int> , val rv : RecyclerView) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myVH {
        val view = LayoutInflater.from(context).inflate(R.layout.each_item_events, parent, false)
        val viewholder = myVH(view)
        return viewholder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val img = list[position]
        (holder as myVH ).bindData(img)
    }

    inner class myVH(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val iv: ImageView

        init {
            iv = itemview.findViewById(R.id.imageView)
        }

        fun bindData(img: Int) {
            iv.setImageResource(img)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}