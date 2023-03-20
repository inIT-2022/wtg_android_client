package ru.sectorsj.where_to_go.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.sectorsj.where_to_go.dto.Location

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
