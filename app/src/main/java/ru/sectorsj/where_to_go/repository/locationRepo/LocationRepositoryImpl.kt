package ru.sectorsj.where_to_go.repository.locationRepo

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.sectorsj.where_to_go.db.dao.LocationDao
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.repository.eventRepo.EventRepositoryImpl.Companion.PAGE_SIZE

@OptIn(ExperimentalPagingApi::class)
class LocationRepositoryImpl(
    private val dao: LocationDao
    ) : LocationRepository {

    override suspend fun getPagedLocations(): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE / 2,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = LocationRemoteMediator(dao),
            pagingSourceFactory = { dao.getPagedLocations() }
        ).flow.map {
            it.map { locationEntity ->
                locationEntity.toDto()
            }
        }
    }

}