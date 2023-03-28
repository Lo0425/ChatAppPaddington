package com.example.chatapppaddington

import android.app.Application
import com.example.chatapppaddington.service.AuthService
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {

    @Inject
    lateinit var authService: AuthService
    var username:String? = null


    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            val res = authService.getCurrentUser()
            username = res?.name
        }
    }
}