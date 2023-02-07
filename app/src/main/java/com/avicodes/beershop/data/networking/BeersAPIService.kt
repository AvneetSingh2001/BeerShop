package com.avicodes.beershop.data.networking

import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersAPIService {

    @GET("/v2/beers")
    suspend fun getAllBeers(
        @Query("page")
        page: Int
    ): Response<Beer>

    @GET("/v2/beers/random")
    suspend fun getRandomBeer(
    ): Response<Beer>
}