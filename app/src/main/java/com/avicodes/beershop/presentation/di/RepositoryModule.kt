package com.avicodes.beershop.presentation.di

import com.avicodes.beershop.data.repository.BeersRepositoryImpl
import com.avicodes.beershop.data.repository.datasource.CacheBeerDataSource
import com.avicodes.beershop.data.repository.datasource.RemoteBeerDataSource
import com.avicodes.beershop.domain.repository.BeersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun getBeersRepository(
        remoteBeerDataSource: RemoteBeerDataSource,
        cacheBeerDataSource: CacheBeerDataSource
    ): BeersRepository {
        return BeersRepositoryImpl(
            remoteBeerDataSource = remoteBeerDataSource,
            cacheBeerDataSource = cacheBeerDataSource
        )
    }
}