package ru.sectorsj.where_to_go.repository.eventRepo

import androidx.lifecycle.LiveData
import ru.sectorsj.where_to_go.dto.Event

interface EventRepository {
    val data: LiveData<List<Event>>
}