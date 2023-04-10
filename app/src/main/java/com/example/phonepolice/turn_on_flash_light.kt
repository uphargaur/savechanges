package com.example.phonepolice

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


lateinit var  flashcontrol :Button
class turn_on_flash_light : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn_on_flash_light)
        flashcontrol = findViewById(R.id.flashlight_button)

        var cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        flashcontrol =findViewById(R.id.flashlight_button)
        var flashoff : Button =findViewById(R.id.flashlight_turnoff)
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