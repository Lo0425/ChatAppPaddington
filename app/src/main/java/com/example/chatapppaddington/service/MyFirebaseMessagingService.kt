package com.example.chatapppaddington.service

import android.content.Intent
import android.util.Log
import com.example.chatapppaddington.data.model.Token
import com.example.chatapppaddington.utils.Constants
import com.example.chatapppaddington.utils.NotificationUtils
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d(Constants.DEBUG, "Token: $token")

        Firebase.firestore.collection("tokens").add(Token(token))
            .addOnSuccessListener {
                Log.d(Constants.DEBUG, "Token saved successfully")
            }

            .addOnFailureListener {
                Log.d(Constants.DEBUG, "Failed to save token")
                it.printStackTrace()
            }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.data["title"].toString()
        val body = message.data["body"].toString()

        if(title == "Broadcast") {
            val intent = Intent()
            intent.action = "com.example.MyBroadcast"
            intent.putExtra("message", body)
            sendBroadcast(intent)
        }

        NotificationUtils.createNotification(
            this,
            title,
            body
        )
    }
}