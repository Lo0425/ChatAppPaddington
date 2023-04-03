package com.example.chatapppaddington.data.repositories

import com.example.chatapppaddington.data.model.User
import com.example.chatapppaddington.domain.repository.UserRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await

class UserRepositoryImp(private val ref: CollectionReference): UserRepository {
    override suspend fun getUsers(): List<User> {
        val res = ref.get().await()
        val users = mutableListOf<User>()
        res.documents.forEach {
            it.toObject(User::class.java)?.let { user ->
                users.add(user)
            }
        }
        return users
    }


}
