package com.avicodes.beershop.data.repository.datasourceImpl

import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import com.avicodes.beershop.data.networking.BeersAPIService
import com.avicodes.beershop.data.repository.datasource.RemoteBeerDataSource
import retrofit2.Response

class RemoteBeerDataSourceImpl(
    private val beersAPIService: BeersAPIService
) : RemoteBeerDataSource {
    override suspend fun getAllBeers(page: Int): Response<Beer> {
        return beersAPIService.getAllBeers(page)
    }

    override suspend fun getRandomBeer(): Response<Beer> {
        return beersAPIService.getRandomBeer()
    }
}