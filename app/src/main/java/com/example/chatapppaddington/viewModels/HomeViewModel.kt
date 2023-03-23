package com.example.chatapppaddington.viewModels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.data.model.Chat
import com.example.chatapppaddington.data.model.Message
import com.example.chatapppaddington.repository.RealTimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val realtimeRepository: RealTimeRepository
) : BaseViewModel() {

    val messages: MutableLiveData<List<Message>> = MutableLiveData()
    val chats: MutableLiveData<List<Chat>> = MutableLiveData(
        listOf(
            Chat(
                "1",
                "John Doe",
                "Jane Doe",
                listOf(
                    Message(
                        "1",
                        "cc",
                        "Sed et tortor eu nunc pharetra blandit ut vitae ligula."
                    )
                )
            ),
            Chat(
                "2",
                "IronMan",
                "Jane Doe",
                listOf(
                    Message(
                        "1",
                        "cc",
                        "Sed et tortor eu nunc pharetra blandit ut vitae ligula."
                    )
                )
            ),
            Chat(
                "3",
                "Captain America",
                "Jane Doe",
                listOf(
                    Message(
                        "1",
                        "cc",
                        "Sed et tortor eu nunc pharetra blandit ut vitae ligula."
                    )
                )
            ),
        )
    )

    override fun onViewCreated() {
        super.onViewCreated()
        viewModelScope.launch {
            realtimeRepository.getAllMessages().collect {
                Log.d("debugging", it.toString())
            }
        }
    }

    fun addMessage() {
        viewModelScope.launch {
            realtimeRepository.addMessage(Message())
        }
    }
}