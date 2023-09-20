package com.example.android.vedantsmessenger

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class Login : Fragment() {


    private lateinit var  lg_email : TextInputEditText
    private lateinit var  lg_password : TextInputEditText
    private  lateinit var  lg_button : Button
    private  lateinit var  main : ScrollView
private  lateinit var progressbar : RelativeLayout
private lateinit var resultLaunch : ActivityResultLauncher<Intent>
    private lateinit var googleSignInOptions : GoogleSignInOptions ;
    private lateinit var mgoogleSignInClients : GoogleSignInClient ;
private val RC_SIGN_IN = 1001 ;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view =  inflater.inflate(R.layout.fragment_login, container, false)
        main = view.findViewById(R.id.main)

        lg_email = view.findViewById(R.id.etlogin_email)
        lg_password = view.findViewById(R.id.etlogin_password)
        lg_button = view.findViewById(R.id.Login_button)
        progressbar = view.findViewById(R.id.login_progess_bar) ;


        lg_button.setOnClickListener {
            val email = lg_email.text.toString();
            val pswrd = lg_password.text.toString() ;
            if(TextUtils.isEmpty(email) ){
                lg_email.error = "Please enter valid emailId"

            }else if( TextUtils.isEmpty(pswrd)){
                lg_password.error = "Enter Valid Password !"

            }else{
            progressbar.visibility = View.VISIBLE ;
            SignIn(email , pswrd ) ;}


        }

        resultLaunch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if( result.resultCode == Activity.RESULT_OK){
               val launchData = result.data ;
               val task = GoogleSignIn.getSignedInAccountFromIntent(launchData)
                try{
                    val account = task.getResult(ApiException::class.java) ;
                    Log.d("Gmail ID" , "FirebaseAuthWIth Google : $account ")
                    FirebaseAuthWithGoogle( account ?. idToken)
                }
                catch (e:ApiException){
                Log.w("Error" , "GoogleSignIn Failed" ,e ) ;


                }
            }
        }
        return view ;
    }

    private fun FirebaseAuthWithGoogle(idToken: String?) {


    }

    private  fun SignIn(email:String , pswrd : String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pswrd).addOnCompleteListener {
            task->
            if( task.isSuccessful){
                Toast.makeText(context ,"Login Succesful" , Toast.LENGTH_LONG).show() ;
//                 val intent =Intent(this,MainActivity::class.java)

//                startActivity(intent);
            }else{

                Toast.makeText(context ,"User Not Found" , Toast.LENGTH_LONG).show() ;
                progressbar.visibility = View.GONE
            }
        }
        ;

    }

}