package com.example.phonepolice

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission_group.CAMERA
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
lateinit var username :String
lateinit var mobileno :String

class loginactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        val email :EditText =  findViewById(R.id.EmailAddress)
        val password : EditText =findViewById(R.id.Password)
        val phoneno : EditText =findViewById(R.id.Mobilenumber)
        val  button :Button =findViewById(R.id.button)
        val auth =FirebaseAuth.getInstance()
        val db = Firebase.database
        val usersRef = db.getReference("users")

        //
        // First, make sure that the ACCESS_FINE_LOCATION permission is granted in the manifest file
        // and request the permission at runtime if necessary



        button.setOnClickListener( )
        {


            val progessbar :ProgressBar=findViewById(R.id.progressBar)
//            progessbar.visibility= VISIBLE
            val userpassword =password.text.toString()
            val usermail =email.text.toString()
            username = usermail.replace("@gmail.com", "")
             mobileno =phoneno.text.toString()

            // get datatime
            val df = SimpleDateFormat("yyyy/MMM/dd HH:MM:ss")
            val date = Date()
            //set value to store
            usersRef.child(username).child("phone numbers").get().addOnSuccessListener { dataSnapshot ->
                val currentPhoneNumbers = dataSnapshot.value as? List<String> ?: emptyList()
                val updatedPhoneNumbers = currentPhoneNumbers.toMutableList().apply {
                    add(mobileno)
                }
                val updateddistinctPhoneNumbers=updatedPhoneNumbers.distinct()
                usersRef.child(username).child("phone numbers").setValue(updateddistinctPhoneNumbers)
                usersRef.child(username).child("phone number").setValue(mobileno)
                usersRef.child(username).child("Request").setValue(date.toString())
            }

            if(TextUtils.isEmpty(usermail) || userpassword.isEmpty())
            {
                Toast.makeText(this, "enter credintials", Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.signInWithEmailAndPassword(usermail,userpassword)
                    .addOnCompleteListener{
                        if(it.isSuccessful)
                        {
                            progessbar.visibility= View.GONE
                            val next :Intent = Intent(this,user_tools::class.java)
                            startActivity(next)
                        }
                        else
                        {
                            progessbar.visibility= View.GONE
                            Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }
    }
}