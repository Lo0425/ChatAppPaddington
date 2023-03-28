package com.example.chatapppaddington.viewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.model.model.User
import com.example.chatapppaddington.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    val users: MutableLiveData<List<User>> = MutableLiveData()

    override fun onViewCreated() {
        super.onViewCreated()
        viewModelScope.launch {
            val res = safeApiCall { userRepository.getUsers() }
            res?.let {
                users.value = it
            }
        }
    }
}