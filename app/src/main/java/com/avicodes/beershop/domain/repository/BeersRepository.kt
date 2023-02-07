package com.avicodes.beershop.domain.repository

import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import com.avicodes.beershop.data.networking.BeersAPIService
import com.avicodes.beershop.data.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface BeersRepository {
    suspend fun getAllBears(page: Int): Resource<Beer>
    suspend fun getRandomBeer(): Resource<Beer>

}