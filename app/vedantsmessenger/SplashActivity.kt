package com.example.android.vedantsmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.google.api.Authentication

class SplashActivity : AppCompatActivity() {

    private lateinit var ImageGif : ImageView
    private lateinit var  topAnim : Animation
    private   val splashScreenTime = 5000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ImageGif = findViewById(R.id.openImage)
         topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim) ;
     ImageGif.animation = topAnim
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this@SplashActivity,AunthenticationActivity::class.java)
                startActivity(intent)
                finish()
            },splashScreenTime.toLong()
        )
    }
}