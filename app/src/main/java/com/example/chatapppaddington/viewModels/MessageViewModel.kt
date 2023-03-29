package com.example.chatapppaddington.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.model.model.Message
import com.example.chatapppaddington.service.AuthService
import com.example.chatapppaddington.model.repository.RealTimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val realTimeRepository: RealTimeRepository,
    private val authService: AuthService,
    private val savedStateHandle: SavedStateHandle
) :
    BaseViewModel() {

    val txt: MutableStateFlow<String> = MutableStateFlow("")
    val uid2 = savedStateHandle.get<String>("id") ?: ""

    fun getAllMessages(uid2: String): Flow<List<Message>> {
        return realTimeRepository.getAllMessages(
            authService.getUserUid() ?: "", uid2
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendMessage() {
        viewModelScope.launch {
            val user = authService.getCurrentUser()
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            val current = LocalDateTime.now().format(formatter)
            user?.let {
                val message = Message(name = user.name, message = txt.value, dateTime = current)
                safeApiCall {
                    realTimeRepository.addMessage(
                        authService.getUserUid() ?: "",
                        uid2,
                        message
                    )
                }
                txt.value = ""
            }
        }
    }

}
