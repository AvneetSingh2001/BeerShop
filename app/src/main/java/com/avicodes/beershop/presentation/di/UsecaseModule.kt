package com.avicodes.beershop.presentation.di

import com.avicodes.beershop.domain.repository.BeersRepository
import com.avicodes.beershop.domain.usecases.GetAllBearsUsecase
import com.avicodes.beershop.domain.usecases.GetRandomBeerUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UsecaseModule {

    @Provides
    @Singleton
    fun provideGetAllBearsUsecase(beersRepository: BeersRepository): GetAllBearsUsecase {
        return GetAllBearsUsecase(
            beersRepository = beersRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetRandomBeerUsecase(
        beersRepository: BeersRepository
    ): GetRandomBeerUsecase {
        return GetRandomBeerUsecase(
            beersRepository = beersRepository
        )
    }
}