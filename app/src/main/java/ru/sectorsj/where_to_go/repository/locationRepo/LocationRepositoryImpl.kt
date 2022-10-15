package ru.sectorsj.where_to_go.repository.locationRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sectorsj.where_to_go.api.LocationApi
import ru.sectorsj.where_to_go.dto.Location
import java.net.ConnectException

class LocationRepositoryImpl() : LocationRepository {
    private val _data = MutableLiveData<List<Location>>()
    override val data: LiveData<List<Location>> = _data

    override suspend fun getAll() {
        try {
            val response = LocationApi.service.getLocations()
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