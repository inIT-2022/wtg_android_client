package ru.sectorsj.where_to_go.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sectorsj.where_to_go.repository.eventRepo.EventRepository
import ru.sectorsj.where_to_go.repository.eventRepo.EventRepositoryImpl
import ru.sectorsj.where_to_go.repository.locationRepo.LocationRepository
import ru.sectorsj.where_to_go.repository.locationRepo.LocationRepositoryImpl
import ru.sectorsj.where_to_go.repository.signin.SignInRepository
import ru.sectorsj.where_to_go.repository.signin.SignInRepositoryImpl
import ru.sectorsj.where_to_go.repository.signup.SignUpRepository
import ru.sectorsj.where_to_go.repository.signup.SignUpRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEventRepository(
        eventRepository: EventRepositoryImpl
    ): EventRepository

    @Binds
    @Singleton
    abstract fun bindLocationRepository(
        locationRepository: LocationRepositoryImpl
    ): LocationRepository

    @Binds
    @Singleton
    abstract fun bindSignUpRepository(
        signUpRepository: SignUpRepositoryImpl
    ): SignUpRepository

    @Binds
    @Singleton
    abstract fun bindSignInRepository(
        signInRepository: SignInRepositoryImpl
    ): SignInRepository

}