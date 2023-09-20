package com.example.android.vedantsmessenger

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.ContentFrameLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.ktx.Firebase


class MenuActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var  toolbarMenu : androidx.appcompat.widget.Toolbar
    private lateinit var  framelayout: FrameLayout
 private lateinit var  OptionValue : String
 private lateinit var  queryTerm : String
 private lateinit var  searchRv : RecyclerView
 private lateinit var  searchLayuotManager : RecyclerView.LayoutManager
 private lateinit var  searchAdapter : SearchAdapter
 private var register : ListenerRegistration?= null
 private val searchInfo = arrayListOf<uuser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
         window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        toolbarMenu = findViewById(R.id.toolbar_menu)
        framelayout = findViewById(R.id.frameLayout)

        if( intent != null ){
           OptionValue = intent.getStringExtra("OptionName").toString()
            when( OptionValue ){
                "profile" ->{
                    framelayout.visibility = View.VISIBLE
                    toolbarMenu.title = "Profile"
                   toolbarMenu.setTitleTextColor(resources.getColor(android.R.color.white)) ; ;

                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout , Profile() ).commit()

                }
                 "Aboutus" ->{
                     framelayout.visibility = View.VISIBLE
                     toolbarMenu.title = "About us"
                     toolbarMenu.setTitleTextColor(resources.getColor(android.R.color.white)) ;
                     supportActionBar?.setDisplayHomeAsUpEnabled(true)

                     supportFragmentManager.beginTransaction().replace(R.id.frameLayout , About_us() ).commit() ;
                 }
                "search" ->{searchRv = findViewById(R.id.rvsearchid)
                    searchLayuotManager = LinearLayoutManager(this)
                    searchRv.visibility = View.VISIBLE
//                      supportFragmentManager.beginTransaction().replace(R.id.frameLayout , Contacts()  ).commit()
                    toolbarMenu.title = "Search Contacts"

                    setSupportActionBar(toolbarMenu)

                    searchRv.addItemDecoration(
                        DividerItemDecoration(
                            searchRv.context,
                            (searchLayuotManager as LinearLayoutManager).orientation
                        )
                    )

                 }
                "friends"->{
                    framelayout.visibility = View.VISIBLE
                    toolbarMenu.title = "friends"
                    toolbarMenu.setTitleTextColor(resources.getColor(android.R.color.white)) ;
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)

                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout , Contacts() ).commit() ;
                }

                "chatmessaging" -> {
                       framelayout.visibility = View.VISIBLE
                    toolbarMenu.title = intent.getStringExtra("receiverName")
                    val fragmentName  = messaging()
                    val transaction = supportFragmentManager.beginTransaction()
                    val bundle = Bundle()
                    bundle.putString("documentID",intent.getStringExtra("chatroom"))
                    bundle.putString("friendNmae",intent.getStringExtra("receiverName"))
                    fragmentName.arguments = bundle
                    transaction.replace(R.id.frameLayout,fragmentName).commit()
                }
                "contactMessaging" -> {
                    framelayout.visibility = View.VISIBLE
                    toolbarMenu.title = intent.getStringExtra("receiverName")
                    val fragmentName  = messaging()
                    val transaction = supportFragmentManager.beginTransaction()
                    val contactbundle = Bundle()
                    contactbundle.putString("chatRoomID",intent.getStringExtra("chatroomID"))
                    contactbundle.putString("friendName",intent.getStringExtra("friendName"))
                    fragmentName.arguments = contactbundle
                    transaction.replace(R.id.frameLayout,fragmentName).commit()
                }
            }


        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search,menu)
        val searchView = menu?.findItem(R.id.search)?.actionView as androidx.appcompat.widget.SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if( query != null ){
            queryTerm = query
            if (queryTerm.isNotEmpty()) {
                searchUsers();
            }
        }
        return true
    }


    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            queryTerm = query
            if (queryTerm.isNotEmpty()) {
                searchUsers();
            }
        }
        return true
    }

    private fun searchUsers() {

        register = FirebaseFirestore.getInstance().collection("users").orderBy("userName").startAt(queryTerm).limit(3)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("onError", "Some Error Occured")
                } else {
                    if (!snapshot?.isEmpty!!) {
                        searchInfo.clear()
                        val searchlist = snapshot.documents
                        for (i in searchlist) {


                            if(FirebaseAuth.getInstance().currentUser!!.uid == i.id){
                                    Log.d("onSuccess" , "USer running the app")
                            }else{


                            val ccontact = uuser(

                                i.getString("userName").toString(),
                                i.getString("userEmail").toString(),
                                i.getString("userStatus").toString(),
                                i.getString("userProfilePhoto").toString(),
                                "0"
                            )
                           searchInfo.add(ccontact)

                            searchAdapter =SearchAdapter(this , searchInfo )
                           searchRv.adapter = searchAdapter
                            searchRv.layoutManager = searchLayuotManager
                            }
                        }
                    }
                }
            }
    }

    override fun onDestroy() {
        register?.remove()
        super.onDestroy()
    }
}