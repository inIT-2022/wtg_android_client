package ru.sectorsj.where_to_go.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sectorsj.where_to_go.dto.Event

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val startDatetime: String?,
    val finishDatetime: String?,
    val linkEventSite: String,
    val price: Int?,
    val location: Long,
    val isActive: Boolean,
    val userCreatedId: Long
) {
    fun toDto() = Event(
        id = id,
        title = title,
        description = description,
        startDatetime = startDatetime,
        finishDatetime = finishDatetime,
        linkEventSite = linkEventSite,
        price = price,
        location = location,
        isActive = isActive,
        userCreatedId = userCreatedId
    )

    companion object {
        fun fromDto(dto: Event) = EventEntity(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            startDatetime = dto.startDatetime,
            finishDatetime = dto.finishDatetime,
            linkEventSite = dto.linkEventSite,
            price = dto.price,
            location = dto.location,
            isActive = dto.isActive,
            userCreatedId = dto.userCreatedId
        )
    }
}

fun List<EventEntity>.fromEntity(): List<Event> = map { it.toDto() }
fun List<Event>.toEntity() : List<EventEntity> = map { EventEntity.fromDto(it) }