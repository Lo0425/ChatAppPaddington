package com.example.chatapppaddington.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.chatapppaddington.common.Resource
import com.example.chatapppaddington.data.model.Message
import com.example.chatapppaddington.domain.repository.RealTimeRepository
import com.example.chatapppaddington.ui.presentation.message.viewModel.MessageEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MessageUseCases @Inject constructor(
    private val realTimeRepository: RealTimeRepository,
) {

    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(event: MessageEvent): Flow<Resource<List<Message>>> {
        return when (event) {
            is MessageEvent.GetMessage -> {
                realTimeRepository
                    .getAllMessages(event.uid1, event.uid2)
                    .map { Resource.Success(it) }
            }

            is MessageEvent.SendMessage -> {
                val (uid1, uid2, msg) = event
                val formatter = DateTimeFormatter.ofPattern("HH:mm")
                val current = LocalDateTime.now().format(formatter)
                flow {
                    realTimeRepository.addMessage(uid1, uid2, msg.copy(dateTime = current))
                    emit(Resource.Success(listOf(), null))
                }
            }
        }
    }

    operator fun invoke(uid1: String, uid2: String): Flow<List<Message>> {
        return realTimeRepository.getAllMessages(uid1, uid2)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(uid1: String, uid2: String, msg: Message) {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val current = LocalDateTime.now().format(formatter)
        realTimeRepository.addMessage(uid1, uid2, msg.copy(dateTime = current))
    }

}