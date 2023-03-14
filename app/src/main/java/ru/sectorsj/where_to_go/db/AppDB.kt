package ru.sectorsj.where_to_go.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.sectorsj.where_to_go.db.dao.EventDao
import ru.sectorsj.where_to_go.db.dao.LocationDao
import ru.sectorsj.where_to_go.db.entity.EventEntity
import ru.sectorsj.where_to_go.db.entity.LocationEntity

@Database(entities = [LocationEntity::class, EventEntity::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun eventDao(): EventDao
}