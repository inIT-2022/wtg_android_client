package ru.sectorsj.where_to_go.model

import ru.sectorsj.where_to_go.enumeration.UserRole

data class Registration(
    val login: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val birthdayDate: String,
    val userRoleString: UserRole = UserRole.ROLE_USER,
)