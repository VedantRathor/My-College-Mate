package com.example.android.vedantsmessenger

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class AunthenticationActivity : AppCompatActivity(), FirebaseAuth.AuthStateListener {

    private lateinit var tab: TabLayout
    private lateinit var viewpager: ViewPager2
    private lateinit var viewpageradapter: AuthenticationViewPager
    private var titles = arrayListOf("Sign Up", "Login")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aunthentication)

        tab = findViewById(R.id.tab_authentication)
        viewpager = findViewById(R.id.viewpager_authentication)
        viewpageradapter = AuthenticationViewPager(this)
        viewpager.adapter = viewpageradapter
        TabLayoutMediator(tab, viewpager) { tab, position ->
            tab.text = titles[position]


        }.attach()


    }

    class AuthenticationViewPager(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 2;
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SignUp();
                1 -> Login();
                else -> SignUp();
            }
        }

    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(this)

        if (FirebaseAuth.getInstance().currentUser != null)

            startMainAct();

    }

    override fun onAuthStateChanged(p0: FirebaseAuth) {
        if (p0.currentUser != null) {
            startMainAct();
        }
    }

    override fun onStop() {
        super.onStop()
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }

    private fun startMainAct() {
        val intent = Intent(this@AunthenticationActivity, MainActivity::class.java)
        startActivity(intent);
    }
}