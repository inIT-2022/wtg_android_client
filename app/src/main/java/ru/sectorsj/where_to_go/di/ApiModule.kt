package ru.sectorsj.where_to_go.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sectorsj.where_to_go.api.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideEventApi(): EventApiService {
       return ConnectionManager.getRetrofitClient(url = BASE_URL).create(EventApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationApi(): LocationApiService {
        return ConnectionManager.getRetrofitClient(url = BASE_URL).create(LocationApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApiService {
        return ConnectionManager.getRetrofitClient(url = AUTH_URL).create(AuthApiService::class.java)
    }
}