package ru.sectorsj.where_to_go.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import ru.sectorsj.where_to_go.db.entity.EventEntity

@Dao
interface EventDao {

    @Query("SELECT * FROM EventEntity ORDER BY id")
    fun getEventPagingSource(): PagingSource<Int, EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(events: List<EventEntity>)

    @Query("DELETE FROM EventEntity")
    suspend fun clear()

    @Transaction
    suspend fun refresh(events: List<EventEntity>) {
        clear()
        insert(events)
    }

}