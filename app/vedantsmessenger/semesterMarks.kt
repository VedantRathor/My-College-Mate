package com.example.android.vedantsmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class semesterMarks : AppCompatActivity() {

     private lateinit var fl : FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semester_marks)

       fl = findViewById(R.id.flsem)

            val obj1 = sem_marks()

            supportFragmentManager.beginTransaction().replace(R.id.flsem,obj1).commit()


    }
}