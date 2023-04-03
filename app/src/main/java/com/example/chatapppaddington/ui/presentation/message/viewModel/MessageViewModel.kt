package com.example.chatapppaddington.ui.presentation.message.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.common.Resource
import com.example.chatapppaddington.data.model.Message
import com.example.chatapppaddington.domain.usecase.MessageUseCases
import com.example.chatapppaddington.service.AuthService
import com.example.chatapppaddington.ui.presentation.base.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val messageUseCases: MessageUseCases,
    private val authService: AuthService,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel() {

    val txt: MutableStateFlow<String> = MutableStateFlow("")
    val messages: MutableStateFlow<List<Message>> = MutableStateFlow(listOf())
    private val uid2 = savedStateHandle.get<String>("id") ?: ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated() {
        super.onViewCreated()
        getAllMessages()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllMessages() {
        messageUseCases(
            MessageEvent.GetMessage(
                authService.getUserUid() ?: "",
                uid2
            )
        ).onEach {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
//                    message.value = it.data!!
                    messages.value = it.data!!
                }

                is Resource.Error -> {
                    error.emit(it.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendMessage() {
        viewModelScope.launch {
            val user = authService.getCurrentUser()
            user?.let {
                val message = Message(name = user.name, message = txt.value)
                messageUseCases(
                    MessageEvent.SendMessage(
                        authService.getUserUid() ?: "",
                        uid2,
                        message
                    )
                ).onEach {
                    when (it) {
                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            txt.value = ""
                        }

                        is Resource.Error -> {
                            error.emit(it.message!!)
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}

