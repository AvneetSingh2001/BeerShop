package com.avicodes.beershop.data.repository.datasourceImpl

import com.avicodes.beershop.data.db.BeerDao
import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import com.avicodes.beershop.data.repository.datasource.CacheBeerDataSource
import kotlinx.coroutines.flow.Flow

class CacheBeerDataSourceImpl(
    private val beerDao: BeerDao
) : CacheBeerDataSource {

    override suspend fun insertToCache(beer: List<BeerItem>) {
        beerDao.insertToCache(
            beer = beer
        )
    }

    override fun getAllBeersFromCache(): List<BeerItem> {
        return beerDao.getAllBeersFromCache()
    }

    override suspend fun deleteBeers(beer: List<BeerItem>) {
        beerDao.deleteBeers(
            beer = beer
        )
    }
}