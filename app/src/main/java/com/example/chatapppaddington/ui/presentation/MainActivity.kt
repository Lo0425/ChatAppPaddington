package com.example.chatapppaddington.ui.presentation


import android.Manifest
import android.app.Activity
import android.app.ForegroundServiceStartNotAllowedException
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.chatapppaddington.R
import com.example.chatapppaddington.data.receiver.MyBroadcastReceiver
import com.example.chatapppaddington.data.receiver.OtpReceiver
import com.example.chatapppaddington.service.MyService
import com.example.chatapppaddington.service.NotificationService
import com.example.chatapppaddington.utils.Constants
import com.example.chatapppaddington.utils.NotificationUtils
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val NOTIFICATION_REQ_CODE = 0
    lateinit var myReceiver: MyBroadcastReceiver
    lateinit var myOtpReceiver: OtpReceiver
    private val FOREGROUND_REQ_CODE = 1
    lateinit var myService: MyService
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main)

        registerBroadCastReceiver()
        registerOtpReceiver()
    }


    private fun registerBroadCastReceiver() {
        NotificationUtils.createNotificationChannel(this)
        checkPermission(
            "android.permission.POST_NOTIFICATIONS",
            NOTIFICATION_REQ_CODE
        )
        checkPermission(
            "android.permission.FOREGROUND_SERVICE",
            FOREGROUND_REQ_CODE
        )

        val filter = IntentFilter()
        filter.addAction("com.example.MyBroadcast")

        myReceiver = MyBroadcastReceiver()
        registerReceiver(myReceiver, filter)
    }


    private fun registerOtpReceiver() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val data = it.data
                    data?.let {
                        val msg = it.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE).toString()
                        val otp = Regex("\\d{4,6}").find(msg)?.value ?: ""
                    }
                }
            }

        myOtpReceiver = OtpReceiver()

        OtpReceiver.bind(object : OtpReceiver.Companion.Listener {
            override fun onSuccess(messageIntent: Intent) {
                resultLauncher.launch(messageIntent)
            }

            override fun onFailure() {

            }
        }
        )

        val otpFilter = IntentFilter()
        otpFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(myOtpReceiver, otpFilter)


    }


    fun startService(){
        myService = MyService()

        Intent(this, MyService::class.java).also{
            intent.putExtra("EXTRA_DATA","Hello from main")
            startService(it)
        }
    }


    fun stopService(){
        Intent(this, MyService::class.java).also {
            stopService(it)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
        unregisterReceiver(myOtpReceiver)
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        } else {
            Log.d(Constants.DEBUG, "Permission is granted already")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            NOTIFICATION_REQ_CODE -> {
                Toast.makeText(this, "Notification permission granted", Toast.LENGTH_LONG).show()
            }

            FOREGROUND_REQ_CODE -> {
                Toast.makeText(
                    this,
                    "Foreground service permission is granted",
                    Toast.LENGTH_LONG
                ).show()
            }
            else -> {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

}