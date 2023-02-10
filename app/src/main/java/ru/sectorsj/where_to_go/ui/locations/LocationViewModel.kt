package ru.sectorsj.where_to_go.ui.locations

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.sectorsj.where_to_go.db.AppDB
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.model.ModelState
import ru.sectorsj.where_to_go.repository.locationRepo.LocationRepositoryImpl

class LocationViewModel(application: Application): AndroidViewModel(application) {
    private val repository = LocationRepositoryImpl(AppDB.getInstance(application).locationDao())
    val data: Flow<List<Location>> = repository.data

    private val _dataState = MutableStateFlow(ModelState())
    val dataState: Flow<ModelState> = _dataState

    init {
        getLocations()
    }


    private fun getLocations() = viewModelScope.launch {
        try {
            _dataState.value = _dataState.value.copy(loading = true)
            repository.getAll()
            _dataState.value = ModelState()
        } catch (e: Exception) {
            _dataState.value = _dataState.value.copy(error = true)
        }
    }

    fun refreshLocations() = viewModelScope.launch {
        try {
            _dataState.value = _dataState.value.copy(refreshing = true)
            repository.getAll()
            _dataState.value = ModelState()
        } catch (e: Exception) {
            _dataState.value = _dataState.value.copy(error = true)
        }
    }
}