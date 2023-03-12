package ru.sectorsj.where_to_go.ui.locations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import ru.sectorsj.where_to_go.db.AppDB
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.repository.locationRepo.LocationRepositoryImpl

@OptIn(ExperimentalCoroutinesApi::class)
class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = LocationRepositoryImpl(AppDB.getInstance(application).locationDao())

    //plans to get locations by search query
    private val searchBy = MutableLiveData("")

    val locationsFlow: Flow<PagingData<Location>> = searchBy.asFlow()
        .flatMapLatest {
            repository.getPagedLocations()
        }.cachedIn(viewModelScope)


}