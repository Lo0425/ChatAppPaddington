package com.example.chatapppaddington.repository

import com.example.chatapppaddington.data.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun editProfile(id: String, user: User)


}