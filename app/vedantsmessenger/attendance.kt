package com.example.android.vedantsmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

import org.w3c.dom.Text

class attendance : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)
        var str : String = "" ;

        var temp = 4
        str += "Nov "
        str += temp.toString()
        str += "\n" ;
        for( i in 1..4){



            if( i == 1 ){
                str += "maths : Present\n"
            }else if( i == 2 ){
                str += "science : Present\n"
            }else if( i == 3 ){
                str += "Big data : Present\n"
            }else{
                str += "BEEE : absent \n"
            }
        }
        temp -= 1
        str += "\n" ;
        str += "Nov "
        str += temp.toString()
        str += "\n" ;
        for( i in 1..4){



            if( i == 1 ){
                str += "maths : absent\n"
            }else if( i == 2 ){
                str += "science : Present\n"
            }else if( i == 3 ){
                str += "Big data : Present\n"
            }else{
                str += "BEEE : absent \n"
            }
        }
        temp -= 1
        str += "\n" ;
        str += "Nov "
        str += temp.toString()
        str += "\n" ;
        for( i in 1..4){



            if( i == 1 ){
                str += "maths : Present\n"
            }else if( i == 2 ){
                str += "science : Present\n"
            }else if( i == 3 ){
                str += "Big data : absent\n"
            }else{
                str += "BEEE : absent \n"
            }
        }

        temp -= 1
        str += "\n" ;
        str += "Nov "
        str += temp.toString()
        str += "\n" ;
        for( i in 1..4){



            if( i == 1 ){
                str += "maths : Absent\n"
            }else if( i == 2 ){
                str += "science : Present\n"
            }else if( i == 3 ){
                str += "Big data : absent\n"
            }else{
                str += "BEEE : absent \n"
            }
        }

        str += "\n" ;
        str += "oct "
        str += "31"
        str += "\n" ;
        for( i in 1..4){



            if( i == 1 ){
                str += "maths : Absent\n"
            }else if( i == 2 ){
                str += "science : Present\n"
            }else if( i == 3 ){
                str += "Big data : absent\n"
            }else{
                str += "BEEE : absent \n"
            }
        }
        val tv  = findViewById<TextView>(R.id.tvattendance)
        tv.text = str
    }
}