package com.example.chatapppaddington.di

import android.content.Context
import android.content.SharedPreferences
import com.example.chatapppaddington.data.service.AuthService
import com.example.chatapppaddington.repository.FireStoreUserRepository
import com.example.chatapppaddington.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyAppDependency {

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        if (sharedPref == null) {
            val key = context.packageName ?: throw NullPointerException("")
            sharedPref = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        }
        return sharedPref!!
    }
    private var sharedPref: SharedPreferences? = null



//    @Provides
//    @Singleton
//    fun gson(): Gson {
//        return Gson()
//    }

    @Provides
    @Singleton
    fun getFireStore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun getFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun getFireStoreUserRepository(db: FirebaseFirestore): UserRepository {
        return FireStoreUserRepository(db.collection("users"))
    }

    @Provides
    @Singleton
    fun getAuthRepository(auth: FirebaseAuth, db: FirebaseFirestore): AuthService {
        return AuthService(auth, db.collection("users"))
    }

//    @Provides
//    @Singleton
//    fun provideStorageService(gson: Gson, sharedPreferences: SharedPreferences): StorageService {
//        return StorageService.getInstance(sharedPreferences, gson)
//    }

}