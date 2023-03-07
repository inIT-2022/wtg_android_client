package ru.sectorsj.where_to_go.repository.signup

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sectorsj.where_to_go.api.AuthApi
import ru.sectorsj.where_to_go.dto.User
import ru.sectorsj.where_to_go.enumeration.UserRole
import ru.sectorsj.where_to_go.model.Authorities
import ru.sectorsj.where_to_go.model.Registration
import java.net.ConnectException

private val empty = User(
    0,
    "",
    null,
    "",
    "",
    "",
    "",
    UserRole.ROLE_USER,
    false,
    listOf(Authorities(0,"","")),
    "",
    false,
    false,
    false
)

class SignUpRepositoryImpl() : SignUpRepository {

    private val _data: MutableStateFlow<User> = MutableStateFlow(empty)
    override val data: StateFlow<User> get() = _data


    override suspend fun signUp(registration: Registration) {
        try {
            val response = AuthApi.service.signUp(registration)
            if (!response.isSuccessful) {
                throw ConnectException("Connection exception")
            }
            val body = response.body() ?: throw Exception("Empty Body")
            _data.value = body
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}