package ru.sectorsj.where_to_go.repository.signin

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sectorsj.where_to_go.api.AuthApiService
import ru.sectorsj.where_to_go.model.Auth
import ru.sectorsj.where_to_go.model.AuthState
import java.net.ConnectException
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : SignInRepository {
    private val _data: MutableStateFlow<AuthState> = MutableStateFlow(AuthState())
    override val data: StateFlow<AuthState> get() = _data

    override suspend fun signIn(auth: Auth) {
        try {
            val response = authApiService.signIn(auth)
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