package com.avicodes.beershop.data.repository.datasource

import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import retrofit2.Response

interface RemoteBeerDataSource {
    suspend fun getAllBeers(page: Int): Response<Beer>
    suspend fun getRandomBeer(): Response<Beer>
}