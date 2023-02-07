package com.avicodes.beershop.data.repository

import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import com.avicodes.beershop.data.repository.datasource.CacheBeerDataSource
import com.avicodes.beershop.data.repository.datasource.RemoteBeerDataSource
import com.avicodes.beershop.data.utils.Resource
import com.avicodes.beershop.domain.repository.BeersRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class BeersRepositoryImpl(
    private val remoteBeerDataSource: RemoteBeerDataSource,
    private val cacheBeerDataSource: CacheBeerDataSource
) : BeersRepository {
    override suspend fun getAllBears(page: Int): Resource<Beer> {
        return getAllBeersFromCache(page)
    }

    override suspend fun getRandomBeer(): Resource<Beer> {
        return getRandomBeerFromRemote()
    }

    suspend fun getAllBeersFromRemote(page: Int): Resource<Beer> {
        val beerResource = responseToResource(remoteBeerDataSource.getAllBeers(page))
        beerResource.data?.let {
            val beerList = it.toList()
            insertToCache(beer = beerList)
        }
        return beerResource
    }


    suspend fun getRandomBeerFromRemote(): Resource<Beer> {
        return responseToResource(remoteBeerDataSource.getRandomBeer())
    }

    suspend fun insertToCache(beer: List<BeerItem>) {
        cacheBeerDataSource.insertToCache(
            beer = beer
        )
    }

    suspend fun getAllBeersFromCache(page: Int): Resource<Beer> {
        val beerList: List<BeerItem>
        try {
            beerList = cacheBeerDataSource.getAllBeersFromCache()

        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
        return if(beerList.size > 1) {
            val beers: Beer = Beer()
            beers.addAll(beerList)
            Resource.Success(beers)
        } else {
            getAllBeersFromRemote(page)
        }
    }

    suspend fun deleteBeers(beer: Beer) {
        cacheBeerDataSource.deleteBeers(
            beer = beer
        )
    }

    fun<T> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }
}