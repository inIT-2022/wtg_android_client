package ru.sectorsj.where_to_go.ui.locations

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
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.repository.locationRepo.LocationRepository
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    //plans to get locations by search query
    private val searchBy = MutableLiveData("")

    val locationsFlow: Flow<PagingData<Location>> = searchBy.asFlow()
        .flatMapLatest {
            repository.getPagedLocations()
        }.cachedIn(viewModelScope)


}