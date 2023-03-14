package ru.sectorsj.where_to_go.ui.events


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.repository.eventRepo.EventRepository
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class EventViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    val eventsFlow: Flow<PagingData<Event>>
    //plans to get events by search query
    private val searchBy = MutableLiveData("")

    init {
        eventsFlow = searchBy.asFlow()
            .flatMapLatest {
                repository.getPagedEvents()
        }.cachedIn(viewModelScope)
    }
}
