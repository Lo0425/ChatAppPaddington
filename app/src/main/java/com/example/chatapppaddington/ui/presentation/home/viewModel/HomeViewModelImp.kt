package com.example.chatapppaddington.ui.presentation.home.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapppaddington.common.Resource
import com.example.chatapppaddington.data.model.User
import com.example.chatapppaddington.domain.repository.UserRepository
import com.example.chatapppaddington.domain.usecase.GetUsersUseCase
import com.example.chatapppaddington.service.AuthService
import com.example.chatapppaddington.ui.presentation.base.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImp @Inject constructor(
//    private val userRepository: UserRepository,
    private val getUsersUseCase: GetUsersUseCase,
    private val authService: AuthService
) : HomeViewModel, BaseViewModel() {

    override val users: MutableLiveData<List<User>> = MutableLiveData()

    override fun onViewCreated() {
        super.onViewCreated()

        getUsersUseCase().onEach {
            when(it){
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    users.value = it.data?.filter { user -> authService.getUserUid()!= user.id }
                }

                is Resource.Error -> {
                    error.emit(it.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}