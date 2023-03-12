package ru.sectorsj.where_to_go.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: Long,
    val title: String,
    val description: String,
    val startDatetime: String?,
    val finishDatetime: String?,
    val linkEventSite: String,
    val linkImage: String,
    val price: Int?,
    val location: Location,
    val isActive: Boolean,
    val userCreatedId: Long,
) : Parcelable
