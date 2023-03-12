package ru.sectorsj.where_to_go.ui.events


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import ru.sectorsj.where_to_go.db.AppDB
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.model.ModelState
import ru.sectorsj.where_to_go.repository.eventRepo.EventRepositoryImpl

@OptIn(ExperimentalCoroutinesApi::class)
class EventViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = EventRepositoryImpl(AppDB.getInstance(application).eventDao())

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
