package com.example.chatapppaddington.ui.presentation.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.chatapppaddington.data.model.User

interface HomeViewModel {
    val users: MutableLiveData<List<User>>
}