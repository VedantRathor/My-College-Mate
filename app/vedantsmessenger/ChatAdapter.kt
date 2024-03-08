package com.example.android.vedantsmessenger

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val SENDER = 1
const val RECEIVER = 2
const val MIDDLE = -1

class ChatAdapter(val context: Activity, val datalist: MutableList<Users>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val obj = datalist[position]
        if (obj.pos == 1) return SENDER
        else if (obj.pos == 2) return RECEIVER
        else return MIDDLE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == SENDER) {
            // right side pe
            val view = LayoutInflater.from(context).inflate(R.layout.sender_item, parent, false)
            val VIEW: senderVH = senderVH(view)
            return VIEW
        } else if (viewType == RECEIVER) {
            // left side pe
            val view = LayoutInflater.from(context).inflate(R.layout.receiver_item, parent, false)
            val VIEW: receiverVH = receiverVH(view)
            return VIEW
        } else {
            // bich me
            val view = LayoutInflater.from(context).inflate(R.layout.info_item, parent, false)
            val VIEW: info = info(view)
            return VIEW
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == SENDER) {
            val dataobj: Users = datalist[position]
            (holder as senderVH).bindDataSender(dataobj)
        } else if (getItemViewType(position) == RECEIVER) {

            (holder as receiverVH).bindDataReceiver(datalist[position])
        } else {
            (holder as info).bindDataInfo(datalist[position])
        }
    }

    // 3 viewtypes = 3 viewHolders!
    inner class senderVH(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private lateinit var tv: TextView

        init {
            tv = itemview.findViewById(R.id.tvSender)
        }

        fun bindDataSender(obj: Users) {
            val name = obj.name
            val msg = obj.msg
            tv.text = "You: $msg"
        }
    }

    inner class receiverVH(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private lateinit var tv: TextView

        init {
            tv = itemview.findViewById(R.id.tvReceiver)
        }

        fun bindDataReceiver(obj: Users) {
            val name = obj.name
            val msg = obj.msg
            tv.text = "${name}: $msg"
        }
    }

    inner class info(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private lateinit var tv: TextView

        init {
            tv = itemview.findViewById(R.id.tvInfo)
        }

        fun bindDataInfo(obj: Users) {
            val name = obj.name
            val msg = obj.msg
            tv.text = "$name $msg"
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}
