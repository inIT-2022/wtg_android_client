package ru.sectorsj.where_to_go.dto

data class Event(
    val id: Long,
    val title: String,
    val description: String,
    val startDatetime: String,
    val finishDatetime: String,
    val linkEventSite: String,
    val price: Int,
    val location: Long,
    val isActive: Boolean,
    val userCreatedId: Long
)
