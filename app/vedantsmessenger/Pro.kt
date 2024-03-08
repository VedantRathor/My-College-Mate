package com.example.android.vedantsmessenger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class Pro : Fragment() {


  private lateinit var btnsemmarks : Button
  private lateinit var btnassign : Button
  private lateinit var btn_reminder : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_pro, container, false)
        val getuid: String? = FirebaseAuth.getInstance().uid
        btnassign = view.findViewById(R.id.btnAssignments)
        btnsemmarks = view.findViewById(R.id.sem_marks)
        btnsemmarks.setOnClickListener {
            val intent : Intent = Intent( this.context , semesterMarks::class.java)
            startActivity(intent) ;

        }

        btnassign.setOnClickListener {
          val intent : Intent = Intent( this.context , assignments_activity::class.java )
            startActivity(intent)
        }

        val btn_attndnce = view.findViewById<Button>(R.id.btn_attendance)
        btn_attndnce.setOnClickListener {
               val intent = Intent( this.context , attendance::class.java)
                 startActivity(intent)
        }

        btn_reminder = view.findViewById(R.id.btn_reminders)
        btn_reminder.setOnClickListener {
            val intent = Intent( this.context , reminders_activity :: class.java )
            startActivity(intent)
        }
        val btntodo = view.findViewById<Button>(R.id.btn_todo)
        btntodo.setOnClickListener {
            val intent = Intent( this.context , todo::class.java )
            startActivity(intent)
        }
        return view ;
    }

}