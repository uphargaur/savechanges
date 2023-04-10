package com.example.phonepolice

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.service.autofill.UserData
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class MyService:Service(){
    var databaseRef: DatabaseReference?=null

    override fun onBind(intent: Intent?): IBinder {

        return null!!
    }

    override fun onCreate() {
        super.onCreate()
        databaseRef= FirebaseDatabase.getInstance().reference
        isServiceRunning=true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // code run in background for long time

        var myLocation= MyLocationListener()
        val locationManager=getSystemService(LOCATION_SERVICE) as LocationManager
        checkLocationPermission()
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation)

        //listsent to request
//        var userData= UserData(this)
        val myPhoneNumber= mobileno
        databaseRef!!.child("Users").child(myPhoneNumber).child("request").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {

                    if (MyService.myLocation==null) return
                    // get datatime
                    val df = SimpleDateFormat("yyyy/MMM/dd HH:MM:ss")
                    val date = Date()
                    databaseRef!!.child("Users").child(myPhoneNumber)
                        .child("location").child("lat").setValue(MyService.myLocation!!.latitude)
                    databaseRef!!.child("Users").child(myPhoneNumber)
                        .child("location").child("log").setValue( MyService.myLocation!!.longitude)
                    databaseRef!!.child("Users").child(myPhoneNumber)
                        .child("location").child("lastOnline").setValue( df.format(date).toString())
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
        )
        return START_NOT_STICKY


    }

    companion object{
        var myLocation: Location?=null
        var isServiceRunning=false
    }

    inner class MyLocationListener: LocationListener {
        constructor():super(){

            myLocation= Location("me")
            myLocation!!.longitude =0.0
            myLocation!!.latitude=0.0
        }


        override fun onLocationChanged(location: Location) {
            myLocation=location
        }


    }
    private fun getpermisions() {
        checkLocationPermission()
    }


    fun checkLocationPermission(){
        val LOCATION_CODE =124
        if(Build.VERSION.SDK_INT>=23){

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED ){

                return
            }
        }

    }

}