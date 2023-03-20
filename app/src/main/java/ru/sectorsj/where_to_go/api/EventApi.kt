package ru.sectorsj.where_to_go.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.sectorsj.where_to_go.dto.Event

interface EventApiService {
    @GET("events")
    suspend fun getEvents(): Response<List<Event>>

    @GET("events/{id}")
    suspend fun getEventById(@Path("id") id: Long): Response<Event>

    @GET("events/after-now")
    suspend fun getPagedEvents(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Response<List<Event>>
}