package ru.sectorsj.where_to_go.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.sectorsj.where_to_go.db.entity.LocationEntity

@Dao
interface LocationDao {
    @Query("SELECT * FROM LocationEntity ORDER BY id")
    fun getPagedLocations(): PagingSource<Int, LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locations: List<LocationEntity>)

    @Query("DELETE FROM LocationEntity")
    suspend fun clear()

    @Transaction
    suspend fun refresh(locations: List<LocationEntity>) {
        clear()
        insert(locations)
    }
}