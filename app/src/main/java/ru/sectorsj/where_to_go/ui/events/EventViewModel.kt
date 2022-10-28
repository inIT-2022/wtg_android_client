package ru.sectorsj.where_to_go.ui.events


import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.sectorsj.where_to_go.db.AppDB
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.model.ModelState
import ru.sectorsj.where_to_go.repository.eventRepo.EventRepositoryImpl

class EventViewModel(application: Application): AndroidViewModel(application) {
    private val repository = EventRepositoryImpl(AppDB.getInstance(application).eventDao())
    val data: LiveData<List<Event>> = repository.data

    private val _dataState = MutableLiveData(ModelState())
    val dataState: LiveData<ModelState> = _dataState

    init {
        getEvents()
    }

    private fun getEvents() = viewModelScope.launch {
        try {
            _dataState.value = _dataState.value?.copy(loading = true)
            repository.getAll()
            _dataState.value = ModelState()
        } catch (e: Exception) {
            _dataState.value = _dataState.value?.copy(error = true)
        }
    }

    fun refreshEvents() = viewModelScope.launch {
        try {
            _dataState.value = _dataState.value?.copy(refreshing = true)
            repository.getAll()
            _dataState.value = ModelState()
        } catch (e: Exception) {
            _dataState.value = _dataState.value?.copy(error = true)
        }
    }
}