package com.avicodes.beershop.presentation.ui.beer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import com.avicodes.beershop.data.utils.Resource
import com.avicodes.beershop.domain.usecases.GetAllBearsUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import retrofit2.Response

class BeersFragmentViewModel(
    private val getAllBearsUsecase: GetAllBearsUsecase
): ViewModel() {

    private var _beersList : MutableLiveData<Resource<Beer>> = MutableLiveData()
    val beerList get() = _beersList

    fun getAllBeers(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        _beersList.postValue(Resource.Loading())
        try {
            val apiResult = getAllBearsUsecase.execute(
                page = page
            )
            _beersList.postValue(apiResult)
        } catch (e: Exception) {
            _beersList.postValue(Resource.Error(e.toString()))
        }
    }
}