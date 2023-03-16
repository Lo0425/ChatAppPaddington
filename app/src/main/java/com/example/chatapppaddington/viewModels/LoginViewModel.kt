package com.example.chatapppaddington.viewModels

import kotlinx.coroutines.flow.MutableSharedFlow

class LoginViewModel: BaseViewModel() {
    val loginFinish: MutableSharedFlow<Unit> = MutableSharedFlow()

}