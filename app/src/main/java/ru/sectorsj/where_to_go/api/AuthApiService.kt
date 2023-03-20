package ru.sectorsj.where_to_go.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.sectorsj.where_to_go.dto.User
import ru.sectorsj.where_to_go.model.Auth
import ru.sectorsj.where_to_go.model.AuthState
import ru.sectorsj.where_to_go.model.Registration

interface AuthApiService {
    @POST("signup")
    suspend fun signUp(@Body registration: Registration): Response<User>

    @POST("login")
    suspend fun signIn(@Body auth: Auth): Response<AuthState>
}