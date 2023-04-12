package com.example.phonepolice

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


lateinit var  flashcontrol :Button
class turn_on_flash_light : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn_on_flash_light)
        flashcontrol = findViewById(R.id.flashlight_button)

        var cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        flashcontrol =findViewById(R.id.flashlight_button)
        var flashoff : Button =findViewById(R.id.flashlight_turnoff)
        //database instialize
        val db = Firebase.database
        val usersRef = db.getReference("users")
        usersRef.child(username).child("Torch").setValue("0")
        var check:Int =1
        usersRef.child(username).child("Torch").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    check=check+1;
                    if(check%2==0) {
                        cameraManager.setTorchMode("0", false)
                    } else {
                        cameraManager.setTorchMode("0", true)
                    }


                }

                override fun onCancelled(error: DatabaseError) {}


            }
        )
        flashcontrol.setOnClickListener()
        {
            cameraManager.setTorchMode("0",true)
        }
        flashoff.setOnClickListener()
        {
            cameraManager.setTorchMode("0",false)
        }
    }
}