package com.example.android.vedantsmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException

const val SERVER_URL = "http://10.0.2.2:80"


class ChatDashboard : AppCompatActivity() {
    private lateinit var code: String
    private lateinit var name: String
    private lateinit var socket: Socket
    private lateinit var rv: RecyclerView
    private lateinit var datalist: MutableList<Users>
    private lateinit var btnsendmsg: Button
    private lateinit var etmsg: EditText

    private fun function(adapter: ChatAdapter) {
        var MIDDLE = -1
        var SENDER = 1
        var RECEIVER = 2

        val obj: JSONObject = JSONObject()
        obj.put("name", name)
        obj.put("msg", "Joined the chat")

        socket.emit("new-user-joined", obj)
        // event of broadcast!
        socket.on("user-joined") { args ->
            runOnUiThread {
                val data = args[0] as? JSONObject ?: return@runOnUiThread
                val name: String
                val msg: String
                try {
                    name = data.getString("name")
                    msg = data.getString("msg")
                    Log.d("---------->", "$name , $msg")
                } catch (e: JSONException) {
                    return@runOnUiThread
                }
                // add the message to view
                datalist.add(Users(name,msg, MIDDLE))
                adapter.notifyItemInserted(datalist.size-1)
            }
        }
        socket.on("receiveMSG") { args ->
            runOnUiThread {
                val data = args[0] as? JSONObject ?: return@runOnUiThread
                val name: String
                val msg: String
                try {
                    name = data.getString("name")
                    msg = data.getString("msg")
                    Log.d("---------->", "$name , $msg")
                } catch (e: JSONException) {
                    return@runOnUiThread
                }
                // add the message to view
                datalist.add(Users(name,msg,RECEIVER))
                adapter.notifyItemInserted(datalist.size-1)
            }
        }
        btnsendmsg.setOnClickListener {
            val msg = etmsg.text.toString()
            etmsg.setText("")
            datalist.add(Users("",msg, SENDER))
            adapter.notifyItemInserted(datalist.size-1)
            socket.emit("sendMSG",msg)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_dashboard)
        code = intent.getStringExtra("CODE").toString()
        name = intent.getStringExtra("NAME").toString()
        datalist = mutableListOf()
        rv = findViewById(R.id.rvchat)
        btnsendmsg = findViewById(R.id.btnSendMsg)
        etmsg = findViewById(R.id.etMessageBox)
        val adapter = ChatAdapter(this@ChatDashboard, datalist)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)

        try {
            // initialize the socket and explicitly connect it!
            socket = IO.socket(SERVER_URL)
            socket.connect()
            function(adapter)

        } catch (e: URISyntaxException) {
            Log.d("EXCEPTION--------", e.toString())
        }
    }
}