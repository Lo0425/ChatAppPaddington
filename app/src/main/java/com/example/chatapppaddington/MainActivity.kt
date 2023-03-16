package com.example.chatapppaddington


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("debugging","testing")

        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")

        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                Log.d("debugging", "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("debugging", "Failed to read value.", error.toException())
            }
        })

        Log.d("ewqewqewq","idk wat this is")

    }
}