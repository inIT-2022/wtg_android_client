package ru.sectorsj.where_to_go.repository.signup

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sectorsj.where_to_go.api.AuthApiService
import ru.sectorsj.where_to_go.dto.User
import ru.sectorsj.where_to_go.enumeration.UserRole
import ru.sectorsj.where_to_go.model.Authorities
import ru.sectorsj.where_to_go.model.Registration
import java.net.ConnectException
import javax.inject.Inject

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
    accountNonExpired = false,
    accountNonLocked = false,
    credentialsNonExpired = false
)

class SignUpRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : SignUpRepository {

    private val _data: MutableStateFlow<User> = MutableStateFlow(empty)
    override val data: StateFlow<User> get() = _data


    override suspend fun signUp(registration: Registration) {
        try {
            val response = authApiService.signUp(registration)
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