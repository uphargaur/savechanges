package com.example.phonepolice

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
private lateinit var locationManager: LocationManager
class user_tools : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_tools)
        val getlocation:Button=findViewById(R.id.getlocation)
        val gettorch:Button=findViewById(R.id.gettorch)
        val Captureimage:Button=findViewById(R.id.Captureimage)
        val cleardata:Button=findViewById(R.id.cleardata)
        getpermisions()
        Savelocation()
        gettorch.setOnClickListener()
        {
            val intent:Intent=Intent(this,turn_on_flash_light::class.java)
            startActivity(intent)
        }
        getlocation.setOnClickListener()
        {
            val intent:Intent=Intent(this,display_mobileno::class.java)
            startActivity(intent)
        }
    }

    private fun getpermisions() {
        checkLocationPermission()
    }


    val LOCATION_CODE =124
    fun checkLocationPermission(){

        if(Build.VERSION.SDK_INT>=23){

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED ){

                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_CODE)
                return
            }
        }

    }

    private fun Savelocation() {
        checkLocationPermission()
        //set location baji
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
       //location renew and call by toper and gps
        val db = Firebase.database
        val usersRef = db.getReference("users")
        usersRef.child(username).child("Request").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    // Get the last known location from the location manager
                    checkLocationPermission()
                    myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    // get datatime
                    val df = SimpleDateFormat("yyyy/MMM/dd HH:MM:ss")
                    val date = Date()
                    println("Onchange location khali wala}")

                    usersRef.child(username).child(mobileno).child("location").child("lat").setValue(myLocation!!.latitude.toString())
                    usersRef.child(username).child(mobileno).child("location").child("log").setValue(myLocation!!.longitude.toString())
                    usersRef.child(username).child(mobileno).child("location").child("last online").setValue(df.format(date).toString())
                }

                override fun onCancelled(error: DatabaseError) {}


        }

        )



    }


    companion object{
        var myLocation: Location?=null
        var isServiceRunning=false
    }
    inner class MyLocationListener: LocationListener {
        constructor() : super() {

            myLocation = Location("me")
            myLocation!!.longitude = 0.1
            myLocation!!.latitude = 0.6
        }

        override fun onLocationChanged(location: Location) {
           println("Onchange location ${location.longitude.toString()}")
            myLocation=location
            myLocation!!.latitude=location.latitude
            myLocation!!.longitude=location.longitude
        }


    }
    }



