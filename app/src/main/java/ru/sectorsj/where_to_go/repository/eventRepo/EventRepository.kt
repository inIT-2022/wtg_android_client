package ru.sectorsj.where_to_go.repository.eventRepo

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.sectorsj.where_to_go.dto.Event

interface EventRepository {
    suspend fun getPagedEvents(): Flow<PagingData<Event>>
}