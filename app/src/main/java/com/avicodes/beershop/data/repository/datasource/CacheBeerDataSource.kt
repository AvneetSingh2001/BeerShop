package com.avicodes.beershop.data.repository.datasource

import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import kotlinx.coroutines.flow.Flow

interface CacheBeerDataSource {
    suspend fun insertToCache(beer: List<BeerItem>)
    fun getAllBeersFromCache(): List<BeerItem>
    suspend fun deleteBeers(beer: List<BeerItem>)
}