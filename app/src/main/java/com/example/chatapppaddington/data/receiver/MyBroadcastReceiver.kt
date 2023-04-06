package com.example.chatapppaddington.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.chatapppaddington.utils.Constants

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(Constants.DEBUG, "Found a broadcast event")

        if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED){
            Toast.makeText(context, "Airplane mode changed", Toast.LENGTH_LONG).show()
        }
    }
}