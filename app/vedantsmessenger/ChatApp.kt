package com.example.android.vedantsmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ChatApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_app)
        val etEnterName = findViewById<EditText>(R.id.etEnterName)
        val etCode = findViewById<EditText>(R.id.etCode)
        val btnChat = findViewById<Button>(R.id.btnEnterRoom)
        btnChat.setOnClickListener {
            it.setBackgroundResource(R.drawable.btn_click_bg)
            val intent = Intent(this, ChatDashboard::class.java)
            intent.putExtra("NAME", etEnterName.text.toString().trim())
            intent.putExtra("CODE", etCode.text.toString().trim())
            startActivity(intent)
        }
    }
}