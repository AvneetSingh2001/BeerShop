package com.avicodes.beershop.presentation.di

import com.avicodes.beershop.presentation.ui.beer.BeersAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideBeersAdapter(): BeersAdapter {
        return BeersAdapter()
    }
}