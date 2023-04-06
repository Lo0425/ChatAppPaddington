package com.example.chatapppaddington.service

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.example.chatapppaddington.utils.Constants
import com.example.chatapppaddington.utils.NotificationUtils

class NotificationService : NotificationListenerService() {

    override fun onCreate() {
        super.onCreate()
        Log.d(Constants.DEBUG, "Running")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        Log.d(Constants.DEBUG, " Found a notification")

        val wNotification = NotificationUtils.getWearableNotification(sbn) ?: return

        val title = wNotification.bundle?.getString("android.title") ?: "Empty"
        if (title.contains("You") || title == "Empty") return

        if (!title.contains(Regex("caaron|lo|nathalie|joel|justin|yan han|xiang|vikram",
            RegexOption.IGNORE_CASE
            ))){

            return
        }
//        if (title == "You" || title == "Empty") return

        val msg = wNotification.bundle?.getString("android.text") ?: "Empty"

        Log.d(Constants.DEBUG, "Title: $title\nBody: $msg")


        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val bundle = Bundle()

        var replyText = "Hello there, I will reply you when I am available"

        if (msg.contains(Regex("hi|hello", RegexOption.IGNORE_CASE))) {
            replyText = "Hello $title"
        }

        bundle.putCharSequence(wNotification.remoteInputs[0].resultKey, replyText)

        RemoteInput.addResultsToIntent(
            wNotification.remoteInputs.toTypedArray(), intent, bundle
        )
        try {
            wNotification.pendingIntent?.let {
                it.send(this, 0, intent)
                if (sbn?.id != null) {
                    NotificationManagerCompat.from(this).cancel(sbn.id)
                } else {
                    cancelNotification(sbn?.key)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        Log.d(Constants.DEBUG,"Destroyed")
        super.onDestroy()
    }

}