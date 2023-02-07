package com.avicodes.beershop.data.db

import androidx.room.*
import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToCache(beer: List<BeerItem>)

    @Query("SELECT * FROM beer_table")
    fun getAllBeersFromCache(): List<BeerItem>

    @Delete
    suspend fun deleteBeers(beer: List<BeerItem>)
}