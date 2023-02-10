package ru.sectorsj.where_to_go.repository.eventRepo

import kotlinx.coroutines.flow.Flow
import ru.sectorsj.where_to_go.dto.Event

interface EventRepository {
    val data: Flow<List<Event>>
    suspend fun getAll()
}