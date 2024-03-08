package com.example.android.vedantsmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class todo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        val tv : TextView = findViewById(R.id.tv_todo)
        val btnadd : Button = findViewById(R.id.btn_add2)

        var str : String = ""
        var t = 1 ;
        for( i in 1..5){
            if( i == 1 ){
                str += "${t.toString()}: DSA\n" ;
                t += 1
            }else if( i == 2 ){
                str += "${t.toString()}: ALGORITHMS\n"
            }else if( i == 3 ){
                str += "${t.toString()}: BIG DATA UNIT 3\n"
            }else if( i == 4 ){
                str += "${t.toString()}: BEEE  2\n"
            }else {
                str += "${t.toString()}: DP\n"
            }
            t += 1
        }

        for( i in 1..5){
            if( i == 1 ){
                str += "${t.toString()}: DSA\n" ;
                t += 1
            }else if( i == 2 ){
                str += "${t.toString()}: ALGORITHMS\n"
            }else if( i == 3 ){
                str += "${t.toString()}: BIG DATA UNIT 3\n"
            }else if( i == 4 ){
                str += "${t.toString()}: BEEE  2\n"
            }else {
                str += "${t.toString()}: DP\n"
            }
            t += 1
        }

        for( i in 1..5){
            if( i == 1 ){
                str += "${t.toString()}: DSA\n" ;
                t += 1
            }else if( i == 2 ){
                str += "${t.toString()}: ALGORITHMS\n"
            }else if( i == 3 ){
                str += "${t.toString()}: BIG DATA UNIT 3\n"
            }else if( i == 4 ){
                str += "${t.toString()}: BEEE  2\n"
            }else {
                str += "${t.toString()}: DP\n"
            }
            t += 1
        }

        tv.text = str

    }
}