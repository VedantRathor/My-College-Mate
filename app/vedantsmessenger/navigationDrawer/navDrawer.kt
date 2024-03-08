package com.example.android.vedantsmessenger.navigationDrawer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.android.vedantsmessenger.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class navDrawer : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var topappbar: MaterialToolbar
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drawer)
        drawerLayout = findViewById(R.id.drawerLayout)
        topappbar = findViewById(R.id.topappbar)
        navView = findViewById(R.id.navViews)

        topappbar.setNavigationOnClickListener {
            drawerLayout.open()

        }

        navView.setNavigationItemSelectedListener { menuitem ->
//            menuitem.isChecked = !menuitem.isChecked
            menuitem.isChecked = true
            drawerLayout.close()
            when (menuitem.itemId) {
                R.id.menu_sem -> {
                    startActivity(Intent(this,testing::class.java))
                }
            }
            true
        }

    }
}