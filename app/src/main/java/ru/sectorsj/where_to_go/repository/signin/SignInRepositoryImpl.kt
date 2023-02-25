package ru.sectorsj.where_to_go.repository.signin

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sectorsj.where_to_go.api.AuthApi
import ru.sectorsj.where_to_go.model.Auth
import ru.sectorsj.where_to_go.model.AuthState
import java.net.ConnectException

class SignInRepositoryImpl() : SignInRepository {
    private val _data: MutableStateFlow<AuthState> = MutableStateFlow(AuthState())
    override val data: StateFlow<AuthState> get() = _data

    override suspend fun signIn(auth: Auth) {
        try {
            val response = AuthApi.service.signIn(auth)
            if (!response.isSuccessful) {
                throw ConnectException("Connection exception")
            }
            val body = response.body() ?: throw Exception("Empty body")
            _data.value = body
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}