package com.example.phonepolice

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signup :Button = findViewById(R.id.button2)
        val login :Button =findViewById(R.id.button3)
        login.setOnClickListener()
        {
            val next =Intent(this,signupactivity::class.java)
            startActivity(next)
        }
        signup.setOnClickListener()
        {
            val next =Intent(this,loginactivity::class.java)
            startActivity(next)
        }
    }
}