package com.example.phonepolice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class signupactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupactivity)
        val email : EditText = findViewById(R.id.email)
        val password: EditText =findViewById(R.id.Password)
        val signupbutton : Button =findViewById(R.id.signbutton)
        val confirmpassword : EditText =findViewById(R.id.ConfirmPassword)
        val auth = FirebaseAuth.getInstance()
        signupbutton.setOnClickListener()
        {

            val useremail =email.text.toString()
            val userpassword =password.text.toString()
            val confirmpass =confirmpassword.text.toString()
            if(TextUtils.isEmpty(useremail) || TextUtils.isEmpty(userpassword))
            {
                Toast.makeText(this,"Username or password cannot be empty", Toast.LENGTH_SHORT).show()
            }
           else if(userpassword.length <6)
            {
                Toast.makeText(this,"password length should be greater than 5", Toast.LENGTH_SHORT).show()
            }
            else if(userpassword !=confirmpass)
            {
                Toast.makeText(this,"confirm password doesnt match", Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.createUserWithEmailAndPassword(useremail,userpassword)
                    .addOnCompleteListener(){
                        if(it.isSuccessful)
                        {
                            Toast.makeText(this,"User is succesfully signed", Toast.LENGTH_SHORT).show()
                            val new :Intent= Intent(this,user_tools::class.java)
                            startActivity(new)
                        }
                        else
                        {
                            Toast.makeText(this,it.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
    }
}
}