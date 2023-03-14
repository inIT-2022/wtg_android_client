package ru.sectorsj.where_to_go.repository.locationRepo

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.sectorsj.where_to_go.db.dao.LocationDao
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.repository.eventRepo.EventRepositoryImpl.Companion.PAGE_SIZE
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class LocationRepositoryImpl @Inject constructor(
    private val dao: LocationDao,
    private val locationRemoteMediator: LocationRemoteMediator
    ) : LocationRepository {

    override suspend fun getPagedLocations(): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE / 2,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = locationRemoteMediator,
            pagingSourceFactory = { dao.getPagedLocations() }
        ).flow.map {
            it.map { locationEntity ->
                locationEntity.toDto()
            }
        }
    }

}