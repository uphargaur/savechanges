package com.example.phonepolice

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signup :Button = findViewById(R.id.button2)
        val login :Button =findViewById(R.id.button3)
        val animationView = findViewById<LottieAnimationView>(R.id.animation_view)
        animationView.playAnimation() // play the animation
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