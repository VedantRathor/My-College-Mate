package com.example.android.vedantsmessenger

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout.TabGravity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Tag
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class SignUp : Fragment() {

 private lateinit var enteremail : TextInputEditText
 private lateinit var enterpassword : TextInputEditText
 private lateinit var confirmPassword : TextInputEditText
 private  lateinit var SignUpButton : Button
 private lateinit var  Progressbar : ProgressBar
 private lateinit var  fauth : FirebaseAuth
 private  lateinit var  fstore : FirebaseFirestore
    private  lateinit var db : DocumentReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up,container,false)
        enteremail = view.findViewById(R.id.etsignup_email)
        enterpassword = view.findViewById(R.id.etsignup_password)
        confirmPassword = view.findViewById(R.id.etsignupCOmfirm_password)
        Progressbar = view.findViewById(R.id.Progress_bar_signup)
        SignUpButton = view.findViewById(R.id.signUpButtonId)

        // initializing fauth , fstore , db
        fauth = FirebaseAuth.getInstance() ;
        fstore = FirebaseFirestore.getInstance() ;

        SignUpButton.setOnClickListener {
            val email = enteremail.text.toString() ;
            val pswrd = enterpassword.text.toString() ;
            val cnfrmPass = confirmPassword.text.toString() ;

           if(TextUtils.isEmpty(email)){
               enteremail.error = "Email is required ! "


           }else if(TextUtils.isEmpty(pswrd)){
               enterpassword.error = " Password is Required !"

           }else if(pswrd.length < 6 ){
               enterpassword.error = "Password must be more than 6 characters !"

           }
            else if(pswrd != cnfrmPass ){
                confirmPassword.error = "Not matching !"
           }
            else{
                Progressbar.visibility = View.VISIBLE
                createAccount(email,pswrd) ;
//               val intent = Intent(activity, MainActivity::class.java)
//               startActivity(intent)


           }


        }



        return view ;
    }

    private fun createAccount(email:String,pswrd:String){

            fauth.createUserWithEmailAndPassword(email,pswrd).addOnCompleteListener{ task->
                if( task.isSuccessful){
                    Progressbar.visibility = View.VISIBLE
                    val userinfo = fauth.currentUser?.uid
                    db = fstore.collection("users").document(userinfo.toString());
                    val obj = mutableMapOf<String,String>()
                    obj["userEmail"] = email ;
                    obj["userPassword"] = pswrd ;
                    obj["userStatus"]=""
                    obj["userName"]=""
                    db.set(obj).addOnSuccessListener {
                        Log.d("onSuccess" , "user created succesfully")
                        Progressbar.visibility = View.VISIBLE }.addOnFailureListener { e -> Log.w( TAG , "Error writing document" , e ) }


                }
            }



    }

}
