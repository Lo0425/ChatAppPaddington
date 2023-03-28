package com.example.chatapppaddington.model.model

data class User(
    val id: String = "",
    val name: String = "",
    val password: String = "",
    val email: String = "",
    ){
    override fun hashCode(): Int {
        var result = id.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}