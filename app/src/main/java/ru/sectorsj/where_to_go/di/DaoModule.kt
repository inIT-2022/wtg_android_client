package ru.sectorsj.where_to_go.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sectorsj.where_to_go.db.AppDB
import ru.sectorsj.where_to_go.db.dao.EventDao
import ru.sectorsj.where_to_go.db.dao.LocationDao


@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    fun provideEventDao(db: AppDB): EventDao {
        return db.eventDao()
    }

    @Provides
    fun provideLocationDao(db: AppDB): LocationDao {
        return db.locationDao()
    }
}