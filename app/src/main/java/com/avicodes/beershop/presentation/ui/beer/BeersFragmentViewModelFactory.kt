package com.avicodes.beershop.presentation.ui.beer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avicodes.beershop.domain.usecases.GetAllBearsUsecase

class BeersFragmentViewModelFactory (
    private val getAllBearsUsecase: GetAllBearsUsecase
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeersFragmentViewModel(
            getAllBearsUsecase = getAllBearsUsecase
        ) as T
    }
}