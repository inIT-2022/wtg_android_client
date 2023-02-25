package ru.sectorsj.where_to_go.model

data class AuthState(
    val token: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val login: String? = null,
    val email: String? = null
)