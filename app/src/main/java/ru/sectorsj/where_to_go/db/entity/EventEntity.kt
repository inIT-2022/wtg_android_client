package ru.sectorsj.where_to_go.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.dto.Location

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Long,
    @ColumnInfo(name = "_title")
    val title: String,
    @ColumnInfo(name = "_description")
    val description: String,
    @ColumnInfo(name = "_startDatetime")
    val startDatetime: String?,
    @ColumnInfo(name = "_finishDateTime")
    val finishDatetime: String?,
    @ColumnInfo(name = "_linkEventSite")
    val linkEventSite: String,
    @ColumnInfo(name = "_price")
    val price: Int?,
    @Embedded
    val location: Location,
    @ColumnInfo(name = "_isActive")
    val isActive: Boolean,
    @ColumnInfo(name = "_userCreatedId")
    val userCreatedId: Long,
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
        userCreatedId = userCreatedId,
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
            userCreatedId = dto.userCreatedId,
        )
    }
}

fun List<EventEntity>.fromEntity(): List<Event> = map { it.toDto() }
fun List<Event>.toEntity() : List<EventEntity> = map { EventEntity.fromDto(it) }