package ru.sectorsj.where_to_go.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sectorsj.where_to_go.dto.Location

@Entity
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String?,
    val fullDescription: String?,
    val address: String,
    val workTimeStart: String?,
    val workTimeEnd: String?,
    val workBreakStart: String?,
    val workBreakEnd: String?,
    val linkImage: String?,
    val linkSite: String?,
    val latitude: Double?,
    val longitude: Double?
) {
    fun toDto() = Location(
        id = id,
        title = title,
        description = description,
        fullDescription = fullDescription,
        address = address,
        workTimeStart = workTimeStart,
        workTimeEnd = workTimeEnd,
        workBreakStart = workBreakStart,
        workBreakEnd = workBreakEnd,
        linkImage = linkImage,
        linkSite = linkSite,
        latitude = latitude,
        longitude = longitude
    )

    companion object {
        fun fromDto(dto: Location) = LocationEntity(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            fullDescription = dto.fullDescription,
            address = dto.address,
            workTimeStart = dto.workTimeStart,
            workTimeEnd = dto.workTimeEnd,
            workBreakStart = dto.workBreakStart,
            workBreakEnd = dto.workBreakEnd,
            linkImage = dto.linkImage,
            linkSite = dto.linkSite,
            latitude = dto.latitude,
            longitude = dto.longitude
        )
    }
}

fun List<LocationEntity>.fromEntity(): List<Location> = map {it.toDto()}
fun List<Location>.toEntity(): List<LocationEntity> = map { LocationEntity.fromDto(it) }
