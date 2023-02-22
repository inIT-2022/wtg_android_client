package ru.sectorsj.where_to_go.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.sectorsj.where_to_go.db.entity.EventEntity

@Dao
interface EventDao {
    @Query("SELECT * FROM EventEntity ORDER BY id")
    fun getAll(): Flow<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(events: List<EventEntity>)
}