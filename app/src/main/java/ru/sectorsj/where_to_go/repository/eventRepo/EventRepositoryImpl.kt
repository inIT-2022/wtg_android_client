package ru.sectorsj.where_to_go.repository.eventRepo

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.sectorsj.where_to_go.db.dao.EventDao
import ru.sectorsj.where_to_go.dto.Event

@OptIn(ExperimentalPagingApi::class)
class EventRepositoryImpl(private val dao: EventDao) :
    EventRepository {

    override suspend fun getPagedEvents(): Flow<PagingData<Event>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE / 2
            ),
            remoteMediator = EventRemoteMediator(dao),
            pagingSourceFactory = { dao.getEventPagingSource() },

        ).flow.map {
            it.map { eventEntity ->
                eventEntity.toDto()
            }
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }

}