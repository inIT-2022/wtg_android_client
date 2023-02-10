package ru.sectorsj.where_to_go.repository.locationRepo

import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.sectorsj.where_to_go.api.LocationApi
import ru.sectorsj.where_to_go.db.dao.LocationDao
import ru.sectorsj.where_to_go.db.entity.LocationEntity
import ru.sectorsj.where_to_go.db.entity.fromEntity
import ru.sectorsj.where_to_go.db.entity.toEntity
import ru.sectorsj.where_to_go.dto.Location
import java.net.ConnectException

class LocationRepositoryImpl(private val dao: LocationDao) : LocationRepository {
    override val data: Flow<List<Location>> = dao.getAll().map(List<LocationEntity>::fromEntity)
        .flowOn(Dispatchers.IO)

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