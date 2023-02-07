package com.avicodes.beershop.data.repository

import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import com.avicodes.beershop.data.networking.BeersAPIService
import com.avicodes.beershop.data.repository.datasource.RemoteBeerDataSource
import com.avicodes.beershop.data.utils.Resource
import com.avicodes.beershop.domain.repository.BeersRepository
import retrofit2.Response

class BeersRepositoryImpl(
    private val remoteBeerDataSource: RemoteBeerDataSource
) : BeersRepository {
    override suspend fun getAllBears(page: Int): Resource<Beer> {
        return responseToResource(remoteBeerDataSource.getAllBeers(page))
    }

    override suspend fun getRandomBeer(): Resource<Beer> {
        return responseToResource(remoteBeerDataSource.getRandomBeer())
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