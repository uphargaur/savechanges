package com.example.phonepolice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class display_mobileno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_mobileno)
        val examplelist :MutableList<exampleitem> = mutableListOf()
        val recyclerView : RecyclerView =findViewById(R.id.recyclerview)
        recyclerView.adapter=adapter(examplelist)
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        //accesing database
        val db = Firebase.database
        val usersRef = db.getReference("users")
        usersRef.child(username).child("phone numbers").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                examplelist.clear()
                var i:Int=1
                for (child in snapshot.children)
                {
                    examplelist.add(exampleitem(i.toString(),child.value.toString()))
                    i=i+1
                }
                (recyclerView.adapter as adapter).notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                println(error.toString())
            }
        })
    }
}