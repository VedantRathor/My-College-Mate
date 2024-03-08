package com.example.android.vedantsmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class reminders_activity : AppCompatActivity() {
    private  lateinit var btnrem : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders)
       btnrem = findViewById(R.id.tv_reminders)
        var str : String = ""
        var t = 1 ;
        for( i in 1..5){
            if( i == 1 ){
                str += "${t.toString()} Your Coding practice is pending\n" ;
                t += 1
            }else if( i == 2 ){
                str += "${t.toString()} Your Tree Algorithms are pending\n"
            }else if( i == 3 ){
                str += "${t.toString()} Big Data Unit 4 pending\n"
            }else if( i == 4 ){
                str += "${t.toString()} BEEE revision unit 2\n"
            }else {
                str += "${t.toString()} Dynamic Programming revision\n"
            }
            t += 1
        }

        for( i in 1..5){
            if( i == 1 ){
                str += "${t.toString()} Your Coding practice is pending\n" ;
            }else if( i == 2 ){
                str += "${t.toString()} Your Tree Algorithms are pending\n"
            }else if( i == 3 ){
                str += "${t.toString()} Big Data Unit 4 pending\n"
            }else if( i == 4 ){
                str += "${t.toString()} BEEE revision unit 2\n"
            }else {
                str += "${t.toString()} Dynamic Programming revision\n"
            }
            t += 1
        }

        for( i in 1..5){
            if( i == 1 ){
                str += "${t.toString()} Your Coding practice is pending\n" ;
            }else if( i == 2 ){
                str += "${t.toString()} Your Tree Algorithms are pending\n"
            }else if( i == 3 ){
                str += "${t.toString()} Big Data Unit 4 pending\n"
            }else if( i == 4 ){
                str += "${t.toString()} BEEE revision unit 2\n"
            }else {
                str += "${t.toString()} Dynamic Programming revision\n"
            }
            t += 1
        }

        btnrem.text = str
    }
}