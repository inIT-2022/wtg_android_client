package ru.sectorsj.where_to_go.repository.locationRepo

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.sectorsj.where_to_go.dto.Location

interface LocationRepository {
    suspend fun getPagedLocations(): Flow<PagingData<Location>>
}