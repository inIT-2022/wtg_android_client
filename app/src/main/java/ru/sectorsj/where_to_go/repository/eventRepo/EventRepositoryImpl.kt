package ru.sectorsj.where_to_go.repository.eventRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import ru.sectorsj.where_to_go.api.EventApi
import ru.sectorsj.where_to_go.db.dao.EventDao
import ru.sectorsj.where_to_go.db.entity.EventEntity
import ru.sectorsj.where_to_go.db.entity.fromEntity
import ru.sectorsj.where_to_go.db.entity.toEntity
import ru.sectorsj.where_to_go.dto.Event
import java.net.ConnectException

class EventRepositoryImpl(private val dao: EventDao) : EventRepository {

    override val data: Flow<List<Event>> = dao.getAll()
        .map(List<EventEntity>::fromEntity)
        .flowOn(Dispatchers.IO)

    override suspend fun getAll() {
        try {
            val response = EventApi.service.getEvents()
            if (!response.isSuccessful) {
                throw ConnectException(response.code().toString())
            }
            val body = response.body() ?: throw Exception("Body is empty")
            dao.insert(body.toEntity())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}