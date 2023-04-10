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
         class MyLocationListener : LocationListener {

            override fun onLocationChanged(location: Location) {
                // Called when a new location is obtained
                val latitude = location.latitude
                val longitude = location.longitude
                // Do something with the location information
                val db = Firebase.database
                val usersRef = db.getReference("users")
                usersRef.child(username).child("Request").addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(p0: DataSnapshot) {
                            // get datatime
                            val df = SimpleDateFormat("yyyy/MMM/dd HH:MM:ss")
                            val date = Date()

                            usersRef.child(username).child(mobileno).child("location").child("lat").setValue(latitude)
                            usersRef.child(username).child(mobileno).child("location").child("log").setValue(longitude)
                            usersRef.child(username).child(mobileno).child("location").child("last online").setValue(df.format(date).toString())
                        }

                        override fun onCancelled(error: DatabaseError) {

                        }


                    }
                )
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                // Called when the status of the GPS provider changes
                // You can handle different status codes here
            }

            override fun onProviderEnabled(provider: String) {
                // Called when the GPS provider is enabled
            }

            override fun onProviderDisabled(provider: String) {
                // Called when the GPS provider is disabled
            }
        }
        //set location baji
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f, MyLocationListener()
        )

    }



    }



