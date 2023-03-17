package ru.sectorsj.where_to_go.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import ru.sectorsj.where_to_go.BuildConfig
import ru.sectorsj.where_to_go.dto.User
import ru.sectorsj.where_to_go.model.Auth
import ru.sectorsj.where_to_go.model.AuthState
import ru.sectorsj.where_to_go.model.Registration
import java.util.concurrent.TimeUnit

private const val AUTH_URL = "http://95.163.237.3:8179/wtg/"

private val interceptor = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun authOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .connectTimeout(5000, TimeUnit.MILLISECONDS)
    .build()

fun authRetrofitClient(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(AUTH_URL)
    .client(okHttpClient)
    .build()

interface AuthApiService {
    @POST("signup")
    suspend fun signUp(@Body registration: Registration): Response<User>

    @POST("login")
    suspend fun signIn(@Body auth: Auth): Response<AuthState>
}