package com.example.chatapppaddington.data.model

data class User(
    val id: String = "",
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val image: String? = null,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (!image.contentEquals(other.image)) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode() ?: 0
        result = 31 * result + username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + image.hashCode()
        return result
    }
}