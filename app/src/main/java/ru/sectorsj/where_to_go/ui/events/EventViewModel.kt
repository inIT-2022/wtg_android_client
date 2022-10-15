package ru.sectorsj.where_to_go.ui.events


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.model.ModelState
import ru.sectorsj.where_to_go.repository.eventRepo.EventRepositoryImpl

class EventViewModel: ViewModel() {
    private val repository = EventRepositoryImpl()
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