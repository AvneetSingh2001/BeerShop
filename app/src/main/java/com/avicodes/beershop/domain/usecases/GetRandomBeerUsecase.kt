package com.avicodes.beershop.domain.usecases

import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import com.avicodes.beershop.data.utils.Resource
import com.avicodes.beershop.domain.repository.BeersRepository

class GetRandomBeerUsecase(
    private val beersRepository: BeersRepository
) {
    suspend fun execute(): Resource<Beer> {
        return beersRepository.getRandomBeer()
    }
}