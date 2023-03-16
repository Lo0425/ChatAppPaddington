package com.example.chatapppaddington.viewModels

import kotlinx.coroutines.flow.MutableSharedFlow

class RegisterViewModel: BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()
}