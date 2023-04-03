package com.example.chatapppaddington.domain.usecase

import com.example.chatapppaddington.common.Resource
import com.example.chatapppaddington.data.model.User
import com.example.chatapppaddington.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(): Flow<Resource<List<User>>> = flow {
        try{
            emit(Resource.Loading())
            val res = userRepository.getUsers()
            emit(Resource.Success(res))
        }catch(e: Exception){
            emit(Resource.Error(e.message ?: ""))
        }
    }
}