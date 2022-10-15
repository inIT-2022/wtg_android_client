package ru.sectorsj.where_to_go.repository.locationRepo

import androidx.lifecycle.LiveData
import ru.sectorsj.where_to_go.dto.Location

interface LocationRepository {
    val data: LiveData<List<Location>>
    suspend fun getAll()
}