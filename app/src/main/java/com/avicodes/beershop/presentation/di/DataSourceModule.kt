package com.avicodes.beershop.presentation.di

import com.avicodes.beershop.data.db.BeerDao
import com.avicodes.beershop.data.networking.BeersAPIService
import com.avicodes.beershop.data.repository.datasource.CacheBeerDataSource
import com.avicodes.beershop.data.repository.datasource.RemoteBeerDataSource
import com.avicodes.beershop.data.repository.datasourceImpl.CacheBeerDataSourceImpl
import com.avicodes.beershop.data.repository.datasourceImpl.RemoteBeerDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun getRemoteBeerDataSource(beersAPIService: BeersAPIService): RemoteBeerDataSource {
        return RemoteBeerDataSourceImpl(
            beersAPIService = beersAPIService
        )
    }


    @Provides
    @Singleton
    fun getCacheBeerDataSource(
        beerDao: BeerDao
    ): CacheBeerDataSource {
        return CacheBeerDataSourceImpl(
            beerDao = beerDao
        )
    }
}