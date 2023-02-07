package com.avicodes.beershop.presentation.di

import com.avicodes.beershop.domain.usecases.GetAllBearsUsecase
import com.avicodes.beershop.domain.usecases.GetRandomBeerUsecase
import com.avicodes.beershop.presentation.ui.beer.BeersFragmentViewModelFactory
import com.avicodes.beershop.presentation.ui.landing.LandingFragmentViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideBeersFragmentViewModelFactory(
        getAllBearsUsecase: GetAllBearsUsecase
    ): BeersFragmentViewModelFactory {
        return BeersFragmentViewModelFactory(
            getAllBearsUsecase = getAllBearsUsecase
        )
    }

    @Provides
    @Singleton
    fun provideLandingFragmentViewModelFactory(
        getRandomBeerUsecase: GetRandomBeerUsecase
    ): LandingFragmentViewModelFactory {
        return LandingFragmentViewModelFactory(
            getRandomBeerUsecase = getRandomBeerUsecase
        )
    }
}