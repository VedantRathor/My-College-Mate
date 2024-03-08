package com.example.android.vedantsmessenger

import android.graphics.Bitmap
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream


class Profile : Fragment() {

    private lateinit var profilenameShow :      TextView
    private lateinit var profileStatusShow :    TextView
    private lateinit var profileEMailShow :     TextView
    private lateinit var profileIMAGEShow : CircleImageView
    private lateinit var profileIMAGE_ADDShow : ImageView
    private lateinit var profile_SAVE__BUTTON : androidx.appcompat.widget.AppCompatButton
    private lateinit var profile_UPDATE__BUTTON : androidx.appcompat.widget.AppCompatButton

    private lateinit var profile_Name_EDIT : TextInputLayout
    private lateinit var profile_status_EDIT : TextInputLayout
    private lateinit var profile_email_EDIT : TextInputLayout
   private lateinit var  profileProgressBar : ProgressBar

    private  lateinit var  profile_name_EDITtext  :TextInputEditText
    private  lateinit var  profile_status_EDITtext  :TextInputEditText
    private  lateinit var  profile_email_EDITtext  :TextInputEditText
 private lateinit var auth : FirebaseAuth
 private lateinit var fstore : FirebaseFirestore
 private  lateinit var  db : DocumentReference
 // for circular image
 private  lateinit var  bitmap : Bitmap
 private lateinit var  storageReference : StorageReference


    private  lateinit var  userID : String
    private lateinit var image : ByteArray
    private lateinit var  iamgeurl : String


    val register = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
        uploadImage(it) ;
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)


        auth = FirebaseAuth.getInstance()
        fstore = FirebaseFirestore.getInstance()
        userID =  auth.currentUser!!.uid
        storageReference = FirebaseStorage.getInstance().reference.child("$userID/profilePhoto")


        profilenameShow = view.findViewById(R.id.ProfileEnterName_id)
        profileStatusShow = view.findViewById(R.id.ProfileEnterStatus_id)
        profileEMailShow = view.findViewById(R.id.ProfileEnterEmailName_id)
        profileIMAGEShow = view.findViewById(R.id.ProfileImage_id)
        profileIMAGE_ADDShow = view.findViewById(R.id.ProfileChangeImage_id)
        profile_SAVE__BUTTON = view.findViewById(R.id.Profilesaveid)
        profile_UPDATE__BUTTON = view.findViewById(R.id.Profileupdateid)
          profile_Name_EDIT = view.findViewById(R.id.ProfileEnterNameEDITTEXT_email)
        profile_status_EDIT = view.findViewById(R.id.ProfileEnterstatusEDITTEXT_email)
        profile_email_EDIT = view.findViewById(R.id.ProfileEnteremailEDITTEXT_email)
         profileProgressBar = view.findViewById(R.id.Profile_progress_bar_ID)

  profile_name_EDITtext = view.findViewById(R.id.etProfileEnterNameEDITTEXT_email)
        profile_email_EDITtext = view.findViewById(R.id.etProfileEnterEmailEDITTEXT_email)
        profile_status_EDITtext = view.findViewById(R.id.etProfileEnterstatusEDITTEXT_email)

        profile_UPDATE__BUTTON.visibility = View.VISIBLE ;

        db = fstore.collection("users").document(userID)





        db.addSnapshotListener { value, error ->
            if( error != null ){
                Log.d("Error" , "Unable to fetch data")
            }else{
                profilenameShow.text = value?.getString("userName")
                profileEMailShow.text = value?.getString("userEmail")
                profileStatusShow.text = value?.getString("userStatus")
                //to show image - picasso - kaise ek url se image ko image view me dikhayege
                Picasso.get().load(value?.getString("userProfilePhoto")).error(R.drawable.add).into(profileIMAGEShow)



            }
        }





        profile_UPDATE__BUTTON.setOnClickListener {
             // sare textview ki visibility hatao

            profilenameShow.visibility = View.GONE ;
            profileStatusShow.visibility = View.GONE ;
            profileEMailShow.visibility = View.GONE ;

            profile_Name_EDIT.visibility = View.VISIBLE
            profile_status_EDIT.visibility = View.VISIBLE
            profile_email_EDIT.visibility = View.VISIBLE
            profile_SAVE__BUTTON.visibility = View.VISIBLE
            profile_UPDATE__BUTTON.visibility = View.GONE

            // aapka data change na ho , jaise maine update pe click kar ke turant save pe click krdiya toh blank data save na hojaye , jo purana hai vahi save hojaye
            profile_name_EDITtext.text = Editable.Factory.getInstance().newEditable(profilenameShow.text.toString())
            profile_status_EDITtext.text = Editable.Factory.getInstance().newEditable(profileStatusShow.text.toString())
            profile_email_EDITtext.text = Editable.Factory.getInstance().newEditable(profileEMailShow.text.toString())




        }

        profile_SAVE__BUTTON.setOnClickListener {
            profilenameShow.visibility = View.VISIBLE ;
            profileStatusShow.visibility = View.VISIBLE ;
            profileEMailShow.visibility = View.VISIBLE;

            profile_Name_EDIT.visibility = View.GONE
            profile_status_EDIT.visibility = View.GONE
            profile_email_EDIT.visibility = View.GONE

            profile_UPDATE__BUTTON.visibility = View.VISIBLE
               profile_SAVE__BUTTON.visibility = View.GONE

            val obj = mutableMapOf<String,String>()
            obj["userName"] = profile_name_EDITtext.text.toString()
            obj["userStatus"] = profile_status_EDITtext.text.toString()
            obj["userEmail"] = profile_email_EDITtext.text.toString()
            db.set(obj).addOnSuccessListener {
                  Log.d("Success" , "Data Succesfully Updated")
              }

        }

        profileIMAGE_ADDShow.setOnClickListener {
            capturephoto() ;
        }


        return view ;

    }

    private fun capturephoto(){

        register.launch(null)

    }
    private fun uploadImage( it : Bitmap? ){
        val baos = ByteArrayOutputStream()
        it?.compress(Bitmap.CompressFormat.JPEG , 100 , baos )
        image = baos.toByteArray()
        //storage reference
        storageReference.putBytes(image).addOnSuccessListener {
            storageReference.downloadUrl.addOnSuccessListener {
                val obj = mutableMapOf<String ,String >()
                obj["userProfilePhoto"] = it.toString()
                db.update(obj as MutableMap<String, Any> ).addOnSuccessListener {
                    Log.d("onSuccess" , "Updated successfully" ) ;

                }
            }
        }
    }


}