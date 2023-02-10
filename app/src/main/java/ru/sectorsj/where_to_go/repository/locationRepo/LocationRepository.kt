package ru.sectorsj.where_to_go.repository.locationRepo

import kotlinx.coroutines.flow.Flow
import ru.sectorsj.where_to_go.dto.Location

interface LocationRepository {
    val data: Flow<List<Location>>
    suspend fun getAll()
}