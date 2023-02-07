package com.avicodes.beershop.domain.usecases

import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.utils.Resource
import com.avicodes.beershop.domain.repository.BeersRepository
import retrofit2.Response

class GetAllBearsUsecase(
    private val beersRepository: BeersRepository
) {
    suspend fun execute(page: Int) : Resource<Beer> {
        return beersRepository.getAllBears(
            page = page
        )
    }
}