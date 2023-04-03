package com.example.chatapppaddington.ui.presentation.login.viewModel

import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.common.Resource
import com.example.chatapppaddington.data.model.User
import com.example.chatapppaddington.domain.usecase.LoginUseCase
import com.example.chatapppaddington.service.AuthService
import com.example.chatapppaddington.ui.presentation.base.viewModel.BaseViewModel
import com.example.chatapppaddington.ui.presentation.login.LoginEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.chatapppaddington.utils.Utils
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {
    val loginFinish: MutableSharedFlow<Unit> = MutableSharedFlow()
    val email: MutableStateFlow<String> = MutableStateFlow("")
    val password: MutableStateFlow<String> = MutableStateFlow("")

    fun login() {
        loginUseCase(
            LoginEvent.Login(
                User(email = email.value, password = password.value)
            )
        ).onEach {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    loginFinish.emit(Unit)
                }

                is Resource.Error -> {
                    error.emit(it.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}