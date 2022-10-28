package ru.sectorsj.where_to_go.repository.locationRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.sectorsj.where_to_go.api.LocationApi
import ru.sectorsj.where_to_go.db.dao.LocationDao
import ru.sectorsj.where_to_go.db.entity.LocationEntity
import ru.sectorsj.where_to_go.db.entity.fromEntity
import ru.sectorsj.where_to_go.db.entity.toEntity
import ru.sectorsj.where_to_go.dto.Location
import java.net.ConnectException

class LocationRepositoryImpl(private val dao: LocationDao) : LocationRepository {
    override val data: LiveData<List<Location>> = dao.getAll().map(List<LocationEntity>::fromEntity)

    override suspend fun getAll() {
        try {
            val response = LocationApi.service.getLocations()
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