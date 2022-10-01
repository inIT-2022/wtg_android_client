package ru.sectorsj.where_to_go.ui.locations

import androidx.lifecycle.ViewModel
import ru.sectorsj.where_to_go.repository.locationRepo.LocationRepositoryImpl

class LocationViewModel: ViewModel() {
    private val repository = LocationRepositoryImpl()
    val data = repository.data
}