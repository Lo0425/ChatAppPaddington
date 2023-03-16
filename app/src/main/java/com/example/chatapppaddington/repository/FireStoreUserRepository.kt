package com.example.chatapppaddington.repository

import com.example.chatapppaddington.data.model.User
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await


class FireStoreUserRepository(private val ref: CollectionReference) : UserRepository {

    override suspend fun getUsers(): List<User> {
        val users = mutableListOf<User>()
        val res = ref.get().await()
        for (document in res) {
            users.add(document.toObject(User::class.java).copy(id = document.id))
        }
        return users
    }

    override suspend fun editProfile(id: String, user: User) {
        ref.document(id).set(user).await()
    }


}