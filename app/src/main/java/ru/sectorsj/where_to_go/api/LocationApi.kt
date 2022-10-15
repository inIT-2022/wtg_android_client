package ru.sectorsj.where_to_go.api

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.sectorsj.where_to_go.dto.Location

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private val okHttp = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttp)
    .build()

interface LocationApiService {
    @GET("locations")
    suspend fun getLocations(): Response<List<Location>>

    @GET("locations/{id}")
    suspend fun getLocationById(@Path("id") id: Long): Response<Location>
}

object LocationApi {
    val service: LocationApiService by lazy {
        retrofit.create(LocationApiService::class.java)
    }
}