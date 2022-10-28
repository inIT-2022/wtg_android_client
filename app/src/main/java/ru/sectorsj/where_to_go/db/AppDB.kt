package ru.sectorsj.where_to_go.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.sectorsj.where_to_go.db.dao.EventDao
import ru.sectorsj.where_to_go.db.dao.LocationDao
import ru.sectorsj.where_to_go.db.entity.EventEntity
import ru.sectorsj.where_to_go.db.entity.LocationEntity

@Database(entities = [LocationEntity::class, EventEntity::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var instance: AppDB? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDB::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}