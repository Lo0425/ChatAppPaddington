package com.example.chatapppaddington.ui.presentation.login

import com.example.chatapppaddington.data.model.User

sealed class LoginEvent{
    data class Login(val user: User): LoginEvent()
}