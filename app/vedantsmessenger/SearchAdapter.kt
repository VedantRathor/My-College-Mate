package com.example.android.vedantsmessenger

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter( val context : Context ,  val searchlist : ArrayList<uuser>):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nname : TextView = view.findViewById(R.id.txtName)
        val email : TextView = view.findViewById(R.id.txtEmail)
        val status : TextView = view.findViewById(R.id.txtStatus)
        val image : ImageView = view.findViewById(R.id.ContactImage_id)
        val addfriend : Button = view.findViewById(R.id.addfriendbtn)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val contactView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_contacts , parent , false )
        return SearchAdapter.SearchViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val list: uuser = searchlist[position]
        val s : String = "Name : "
        val s2 : String = "Status : "
        holder.nname.text =  "$s" + list.profileName
        holder.email.text = list.profileEmail
        holder.status.text = "$s2" + list.profileStatus
        Picasso.get().load(list.profilePicture).error(R.drawable.add ).into(holder.image)


//        holder.image.text = list.profilePicture

        FirebaseFirestore.getInstance()
            .collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection("friends").whereEqualTo(FieldPath.documentId(),list.profileEmail)
            .addSnapshotListener { value, error ->
                if( error != null ){
                    Log.e("onError" , "some erroe occured")
                }else{
                    if( !value?.isEmpty!!){
                       holder.addfriend.visibility = View.GONE
                    }else{
                        holder.addfriend.visibility = View.VISIBLE
                    }
                }
            }
        holder.addfriend.setOnClickListener {
            val c = Calendar.getInstance(Locale.getDefault())
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val mins = c.get(Calendar.MINUTE)
            val timeStamp = "$hour:$mins"
            val obj = mutableMapOf<String,String>().also {
                it["time"] = timeStamp
            }
            val uEmail1 = FirebaseAuth.getInstance().currentUser?.uid.toString()
            val uEmail2 = list.profileEmail
            FirebaseFirestore.getInstance()
                .collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .collection("friends").document(list.profileEmail).set(obj).addOnSuccessListener {
                    Log.d("onSuccess" , "succesfully added with")
                }
            val obj1 = mutableMapOf<String,ArrayList<String>>().also {
                it["uids"] = arrayListOf(uEmail1,uEmail2)

            }
            FirebaseFirestore.getInstance().collection("chats").document().set(obj1)
                .addOnSuccessListener {
                    Log.d("onSuccess" , "succesfully added with")
                }
        }
    }

    override fun getItemCount(): Int {
        return searchlist.size
    }

}
