package com.example.chatapppaddington.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.data.model.Message
import com.example.chatapppaddington.data.service.AuthService
import com.example.chatapppaddington.repository.RealTimeRepository
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
    suspend fun getAllMessages(): Flow<List<Message>> {
        return realTimeRepository.getAllMessages("I8KHcM3LQ7QJj5pFrQogTP4nxgp2","hIcPgsh8V7e0KLJWxmMbEFsMI3h1")
    }

    fun sendMessage(msg: String) {
        viewModelScope.launch {
            val user = authService.getCurrentUser()
            user?.let{
                val message = Message(name = user.name ?: "", message = msg)
                safeApiCall {  realTimeRepository.addMessage(user.id, "hIcPgsh8V7e0KLJWxmMbEFsMI3h1",message) }
            }
        }
    }

}
