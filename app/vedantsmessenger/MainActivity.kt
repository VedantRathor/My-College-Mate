package com.example.android.vedantsmessenger

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
  private var backPressedTime : Long = 0 ;
 private lateinit var showContactButton : FloatingActionButton
 private lateinit var addnotiBtn : FloatingActionButton

    private lateinit var viewpager2 : ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var toolbar : androidx.appcompat.widget.Toolbar
    private lateinit var appPagerAdapter : AppPagerAdapter
    private val titles = arrayListOf("Notifications" , "Status" , "more","" )
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       showContactButton = findViewById(R.id.showContactButton)

        addnotiBtn = findViewById(R.id.showaddButton)
        toolbar = findViewById(R.id.toolbarMain)
        auth = FirebaseAuth.getInstance()
        tabLayout = findViewById(R.id.TabtoolbartoolbarMain)
        viewpager2 = findViewById(R.id.viewpager2Main)
        toolbar.title = "Chatting App"
        toolbar.setTitleTextColor((resources.getColor(android.R.color.white)))
        setSupportActionBar(toolbar)
        appPagerAdapter = AppPagerAdapter(this)
        viewpager2.adapter = appPagerAdapter



      TabLayoutMediator(tabLayout,viewpager2){
          tab,position ->
          tab.text = titles[position]

      }.attach()


        showContactButton.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            intent.putExtra("OptionName" , "friends")
            startActivity(intent) ;
        }


    }
//
//    override fun onBackPressed() {
//        if (backPressedTime + 3000 > System.currentTimeMillis()) {
//            super.onBackPressed()
//            finish()
//        } else {
//            Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG).show()
//        }
//        backPressedTime = System.currentTimeMillis()
//    }

    class AppPagerAdapter( fragmentActivity : FragmentActivity) :FragmentStateAdapter(fragmentActivity){
        override fun getItemCount(): Int {
  return 4 ;
        }

        override fun createFragment(position: Int): Fragment {
             return when(position){
                 0 -> Chats() ;

                 1 -> Status() ;

                 2 -> Pro()
                 else->Chats() ;
             }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.Id_profile ->{
                val intent = Intent(this,MenuActivity::class.java)
                 intent.putExtra("OptionName" , "profile")
                        startActivity(intent) ;
            }
            R.id.id_Logout ->{ Toast.makeText(this,"Logging Out", Toast.LENGTH_LONG).show()
                           auth.signOut() ;
                val intent = Intent(this,AunthenticationActivity::class.java)
                startActivity(intent) ;
                finish()

            }
            R.id.id_aboutUs -> {Toast.makeText(this,"About us clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this,MenuActivity::class.java)
                intent.putExtra("OptionName" , "Aboutus")
                startActivity(intent) ;
            }
            R.id.searchcontactsID ->{
                val intent = Intent(this,MenuActivity::class.java)
                intent.putExtra("OptionName" , "search")
                startActivity(intent) ;
            }
            R.id.chatid -> {
                val intent = Intent( this , MenuActivity::class.java)
                intent.putExtra("OptionName","Chat")
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}