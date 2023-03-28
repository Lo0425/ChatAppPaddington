package com.example.chatapppaddington.viewModels.auth

import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.service.AuthService
import com.example.chatapppaddington.viewModels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.chatapppaddington.utils.Utils

@HiltViewModel
class LoginViewModel @Inject constructor(private val auth: AuthService): BaseViewModel() {
    val loginFinish: MutableSharedFlow<Unit> = MutableSharedFlow()
    val email: MutableStateFlow<String> = MutableStateFlow("")
    val password: MutableStateFlow<String> = MutableStateFlow("")
    fun login() {
        if(Utils.validate(email.value , password.value)){
            viewModelScope.launch {
                safeApiCall {
                    auth.login(email.value, password.value)
                    loginFinish.emit(Unit)
                }
            }
        } else {
            viewModelScope.launch {
                error.emit("Login failed")

            }
        }
    }


}