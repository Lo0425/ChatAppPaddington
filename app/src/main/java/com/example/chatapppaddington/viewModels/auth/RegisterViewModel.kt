package com.example.chatapppaddington.viewModels.auth

import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.model.model.User
import com.example.chatapppaddington.service.AuthService
import com.example.chatapppaddington.utils.Utils
import com.example.chatapppaddington.viewModels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepo: AuthService) : BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()
    val name: MutableStateFlow<String> = MutableStateFlow("")
    val email: MutableStateFlow<String> = MutableStateFlow("")
    val password: MutableStateFlow<String> = MutableStateFlow("")


    fun register() {
        if (Utils.validate(name.value, email.value, password.value)) {
            viewModelScope.launch {
                safeApiCall {
                    authRepo.createUser(
                        User(
                            name.value,
                            password.value,
                            email.value
                        )
                    )
                }
                finish.emit(Unit)
            }
        } else {
            viewModelScope.launch {
                error.emit("Please provide all the information")
            }
        }

    }
}
