package ru.sectorsj.where_to_go.model

data class ModelState(
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val error: Boolean = false
)