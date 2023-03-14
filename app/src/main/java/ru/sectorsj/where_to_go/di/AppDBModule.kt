package ru.sectorsj.where_to_go.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.sectorsj.where_to_go.db.AppDB
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppDBModule {

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext context: Context): AppDB {
        return Room.databaseBuilder(context, AppDB::class.java, "app.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}