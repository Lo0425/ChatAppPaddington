package com.example.chatapppaddington.viewModels

import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.model.model.Message
import com.example.chatapppaddington.service.AuthService
import com.example.chatapppaddington.model.repository.RealTimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val realTimeRepository: RealTimeRepository,
    private val authService: AuthService
) :
    BaseViewModel() {
    fun getAllMessages(uid2: String): Flow<List<Message>> {
        return realTimeRepository.getAllMessages(authService.getUserUid() ?: "", uid2)
    }

    fun sendMessage(uid2: String, msg: String) {
        viewModelScope.launch {
            val user = authService.getCurrentUser()
            user?.let {
                val message = Message(name = user.name ?: "", message = msg)
                safeApiCall {
                    realTimeRepository.addMessage(
                        authService.getUserUid() ?: "",
                        uid2,
                        message
                    )
                }
            }
        }
    }

}
