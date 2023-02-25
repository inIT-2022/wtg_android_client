package ru.sectorsj.where_to_go.dto

import ru.sectorsj.where_to_go.enumeration.UserRole
import ru.sectorsj.where_to_go.model.Authorities

data class User(
    val id: Long,
    val login: String,
    val email: String,
    val password: String?,
    val firstName: String,
    val lastName: String,
    val birthdayDate: String,
    val userRoleString: UserRole,
    val enabled: Boolean,
    val authorities: List<Authorities>,
    val username: String,
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val credentialsNonExpired: Boolean
)
