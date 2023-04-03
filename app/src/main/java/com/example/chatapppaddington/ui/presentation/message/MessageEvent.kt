package com.example.chatapppaddington.ui.presentation.message.viewModel

import com.example.chatapppaddington.data.model.Message

sealed class MessageEvent {
    data class SendMessage(
        val uid1: String,
        val uid2: String,
        val msg: Message
    ) : MessageEvent()

    data class GetMessage(
        val uid1: String,
        val uid2: String
    ) : MessageEvent()
}