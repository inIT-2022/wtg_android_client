package ru.sectorsj.where_to_go.repository.eventRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sectorsj.where_to_go.api.EventApi
import ru.sectorsj.where_to_go.dto.Event
import java.net.ConnectException

class EventRepositoryImpl() : EventRepository {
    private val _data = MutableLiveData<List<Event>>()
    override val data: LiveData<List<Event>> = _data

    override suspend fun getAll() {
        try {
            val response = EventApi.service.getEvents()
            if (!response.isSuccessful) {
                throw ConnectException(response.code().toString())
            }
            val body = response.body() ?: throw Exception("Body is empty")
            _data.postValue(body)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}