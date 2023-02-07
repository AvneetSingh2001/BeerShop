package com.avicodes.beershop.presentation.di

import android.app.Application
import androidx.room.Room
import com.avicodes.beershop.data.db.BeerDao
import com.avicodes.beershop.data.db.BeerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideBeerDatabase(app: Application) : BeerDatabase{
        return Room.databaseBuilder(app, BeerDatabase::class.java, "beer_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(beerDatabase: BeerDatabase) : BeerDao {
        return beerDatabase.beerDao()
    }

}