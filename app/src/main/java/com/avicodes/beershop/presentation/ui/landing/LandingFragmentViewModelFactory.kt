package com.avicodes.beershop.presentation.ui.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avicodes.beershop.domain.usecases.GetRandomBeerUsecase

class LandingFragmentViewModelFactory(
    private val getRandomBeerUsecase: GetRandomBeerUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LandingFragmentViewModel(
            getRandomBeerUsecase = getRandomBeerUsecase
        ) as T
    }
}