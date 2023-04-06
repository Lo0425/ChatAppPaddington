package com.example.chatapppaddington.data.model

import android.app.PendingIntent
import android.os.Bundle

data class WearableNotification(
    val tag: String?,
    val name: String,
    val pendingIntent: PendingIntent?,
    val remoteInputs: ArrayList<android.app.RemoteInput>,
    val bundle: Bundle?
)