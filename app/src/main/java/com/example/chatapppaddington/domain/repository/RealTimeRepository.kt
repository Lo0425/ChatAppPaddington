package com.example.chatapppaddington.domain.repository

import com.example.chatapppaddington.data.model.Message
import kotlinx.coroutines.flow.Flow



interface RealTimeRepository{
    suspend fun addMessage(msg: Message)

    suspend fun addMessage(uid1: String, uid2: String, msg: Message)

    fun getAllMessages(uid1: String, uid2: String): Flow<List<Message>>

    fun getAllMessages(): Flow<List<Message>>
}

