package ru.sectorsj.where_to_go.api

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.sectorsj.where_to_go.dto.Location

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun locationOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

fun locationRetrofitClient(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface LocationApiService {
    @GET("locations")
    suspend fun getLocations(): Response<List<Location>>

    @GET("locations")
    suspend fun getPagedLocations(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<List<Location>>

    @GET("locations/{id}")
    suspend fun getLocationById(@Path("id") id: Long): Response<Location>
}
