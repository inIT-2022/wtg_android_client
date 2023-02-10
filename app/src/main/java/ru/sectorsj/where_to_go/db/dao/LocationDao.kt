package ru.sectorsj.where_to_go.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.sectorsj.where_to_go.db.entity.LocationEntity

@Dao
interface LocationDao {
    @Query("SELECT * FROM LocationEntity ORDER BY id")
    fun getAll(): Flow<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locations: List<LocationEntity>)
}