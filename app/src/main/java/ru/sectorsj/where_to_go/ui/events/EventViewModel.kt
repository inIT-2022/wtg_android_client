package ru.sectorsj.where_to_go.ui.events


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.sectorsj.where_to_go.db.AppDB
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.model.ModelState
import ru.sectorsj.where_to_go.repository.eventRepo.EventRepositoryImpl

class EventViewModel(application: Application): AndroidViewModel(application) {
    private val repository = EventRepositoryImpl(AppDB.getInstance(application).eventDao())
    val data: Flow<List<Event>> = repository.data

    private val _dataState = MutableStateFlow(ModelState())
    val dataState: Flow<ModelState> = _dataState

    init {
        getEvents()
    }

    private fun getEvents() = viewModelScope.launch {
        try {
            _dataState.value = _dataState.value.copy(loading = true)
            repository.getAll()
            _dataState.value = ModelState()
        } catch (e: Exception) {
            _dataState.value = _dataState.value.copy(error = true)
        }
    }

    fun refreshEvents() = viewModelScope.launch {
        try {
            _dataState.value = _dataState.value.copy(refreshing = true)
            repository.getAll()
            _dataState.value = ModelState()
        } catch (e: Exception) {
            _dataState.value = _dataState.value.copy(error = true)
        }
    }
}