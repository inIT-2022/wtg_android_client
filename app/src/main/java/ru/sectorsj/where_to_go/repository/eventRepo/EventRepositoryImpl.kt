package ru.sectorsj.where_to_go.repository.eventRepo

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.sectorsj.where_to_go.db.dao.EventDao
import ru.sectorsj.where_to_go.dto.Event
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class EventRepositoryImpl @Inject constructor(
    private val dao: EventDao,
    private val eventRemoteMediator: EventRemoteMediator
    ) :
    EventRepository {

    override suspend fun getPagedEvents(): Flow<PagingData<Event>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE / 2
            ),
            remoteMediator = eventRemoteMediator,
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