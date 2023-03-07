package ru.sectorsj.where_to_go.repository.signin

import kotlinx.coroutines.flow.StateFlow
import ru.sectorsj.where_to_go.model.Auth
import ru.sectorsj.where_to_go.model.AuthState

interface SignInRepository {
    val data: StateFlow<AuthState>
    suspend fun signIn(auth: Auth)
}