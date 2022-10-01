package ru.sectorsj.where_to_go.ui.events

import androidx.lifecycle.ViewModel
import ru.sectorsj.where_to_go.repository.eventRepo.EventRepositoryImpl

class EventViewModel: ViewModel() {
    private val repository = EventRepositoryImpl()
    val data = repository.data
}