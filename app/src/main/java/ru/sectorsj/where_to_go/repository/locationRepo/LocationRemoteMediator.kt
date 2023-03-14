package ru.sectorsj.where_to_go.repository.locationRepo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import ru.sectorsj.where_to_go.api.LocationApiService
import ru.sectorsj.where_to_go.db.dao.LocationDao
import ru.sectorsj.where_to_go.db.entity.LocationEntity
import ru.sectorsj.where_to_go.db.entity.toEntity
import java.net.ConnectException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class LocationRemoteMediator
    @Inject constructor(
    private val locationDao: LocationDao,
    private val locationApiService: LocationApiService
) : RemoteMediator<Int, LocationEntity>() {
    private var pageIndex = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationEntity>
    ): MediatorResult {
        pageIndex =
            getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)
        val pageSize = state.config.pageSize
        try {
            val response = locationApiService.getPagedLocations(pageIndex, pageSize)
            if (!response.isSuccessful) throw ConnectException(response.code().toString())
            val body = response.body() ?: throw Exception("Body is empty")
            if (loadType == LoadType.REFRESH) {
                locationDao.refresh(body.toEntity())
            } else {
                locationDao.insert(body.toEntity())
            }
            return MediatorResult.Success(endOfPaginationReached = body.size < pageSize)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }
}