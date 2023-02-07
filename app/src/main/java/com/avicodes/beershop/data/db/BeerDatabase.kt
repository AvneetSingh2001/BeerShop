package com.avicodes.beershop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem

@Database(
    entities = [BeerItem::class],
    version = 1,
    exportSchema = false
)
abstract class BeerDatabase: RoomDatabase() {
    abstract fun beerDao() : BeerDao
}