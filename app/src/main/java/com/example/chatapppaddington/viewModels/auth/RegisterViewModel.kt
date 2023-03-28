package com.example.chatapppaddington.viewModels.auth

import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.model.model.User
import com.example.chatapppaddington.service.AuthService
import com.example.chatapppaddington.utils.Utils
import com.example.chatapppaddington.viewModels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepo: AuthService) : BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun register(
        id: String,
        name: String,
        email: String,
        pass: String,

        ) {
        if (Utils.validate(name, email, pass)) {
            viewModelScope.launch {
                safeApiCall {
                    authRepo.createUser(
                        User(
                            id,
                            name,
                            pass,
                            email
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
