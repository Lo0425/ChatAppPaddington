package com.example.chatapppaddington.domain.repository

import com.example.chatapppaddington.data.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}