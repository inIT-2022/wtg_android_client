package ru.sectorsj.where_to_go.dto

data class Location(
    val id: Long,
    val title: String,
    val description: String,
    val fullDescription: String,
    val address: String,
    val workTimeStart: String,
    val workTimeEnd: String,
    val workBreakStart: String,
    val workBreakEnd: String,
    val linkImage: String,
    val linkSite: String,
    val latitude: Double,
    val longitude: Double
)
