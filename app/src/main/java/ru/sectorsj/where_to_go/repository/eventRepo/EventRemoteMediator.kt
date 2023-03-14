package ru.sectorsj.where_to_go.repository.eventRepo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import ru.sectorsj.where_to_go.api.EventApiService
import ru.sectorsj.where_to_go.db.dao.EventDao
import ru.sectorsj.where_to_go.db.entity.EventEntity
import ru.sectorsj.where_to_go.db.entity.toEntity
import java.net.ConnectException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class EventRemoteMediator @Inject constructor(
    private val eventDao: EventDao,
    private val eventApi: EventApiService
    ): RemoteMediator<Int, EventEntity>() {
    private var pageIndex = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EventEntity>
    ): MediatorResult {

        pageIndex = getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)
        val limit = state.config.pageSize

        try {
            val response = eventApi.getPagedEvents(pageIndex, limit)
            if (!response.isSuccessful) throw ConnectException(response.code().toString())
            val body = response.body() ?: throw Exception("Body is empty")
            if (loadType == LoadType.REFRESH) {
                eventDao.refresh(body.toEntity())
            } else {
                eventDao.insert(body.toEntity())
            }
            return MediatorResult.Success(
                endOfPaginationReached = body.size < limit
            )
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when(loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }
}