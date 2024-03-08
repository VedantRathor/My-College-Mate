package com.example.android.vedantsmessenger

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adp_ass(var list : ArrayList<dc> ) : RecyclerView.Adapter<adp_ass.vholder>() {
    inner class vholder(item: View) : RecyclerView.ViewHolder(item)
private lateinit var tv1 : TextView
private lateinit var tv2 : TextView


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vholder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.assignxml, parent, false)
           tv1 = view.findViewById(R.id.tv_sub_name)
            tv2 = view.findViewById(R.id.id_status)

        return vholder(view) ;
    }

    override fun onBindViewHolder(holder: vholder, position: Int) {
      holder.itemView.apply {
          tv1.text = list[position].str

      }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}