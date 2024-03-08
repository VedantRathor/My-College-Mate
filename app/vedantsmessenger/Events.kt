package com.example.android.vedantsmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy

class Events : AppCompatActivity() {
    private lateinit var  rv : RecyclerView
    private lateinit var  rveventlist : RecyclerView
    private lateinit var  list : MutableList<Int>
    private lateinit var  listText : MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_events)
        rv = findViewById(R.id.rvEvents)
        rveventlist = findViewById(R.id.eventsRV)

        list = mutableListOf()
        list.add(R.drawable.imgone)
        list.add(R.drawable.imgtwo)
        list.add(R.drawable.imgthree)
        list.add(R.drawable.imgfour)

        listText = mutableListOf()
        listText.add("Event: C++ Hackathon")
        listText.add("Event: Hashcode Hackathon")
        listText.add("Event: Sem 4 vs Sem 5")
        listText.add("Event: Sem 6 vs Sem 7")
        listText.add("Event: Hacktober-7")
        listText.add("Event: Hacktober-10")
        listText.add("Event: Android Hack-Hash 001")
        listText.add("Event: Android Hack-Hash 002")
        listText.add("Event: Android Hack-Hash 003")
        listText.add("Event: Android Hack-Hash 004")
        listText.add("Event: Android Hack-Hash 004")
        listText.add("Event: Android Hack-Hash 004")
        listText.add("Event: Sem 6 vs Sem 7")
        listText.add("Event: Sem 6 vs Sem 7")
        listText.add("Event: Sem 6 vs Sem 7")
        listText.add("Event: Sem 6 vs Sem 7")


        val adapter = adapterEvent( this@Events , list , rv )
        val SnapHelper = LinearSnapHelper()
        SnapHelper.attachToRecyclerView(rv)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        rv.adapter = adapter

        val adapter2 = adapterEventList( this@Events , listText  )

        rveventlist.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rveventlist.adapter = adapter2
        val SnapHelperw = LinearSnapHelper()
        SnapHelperw.attachToRecyclerView(rveventlist)

    }
}