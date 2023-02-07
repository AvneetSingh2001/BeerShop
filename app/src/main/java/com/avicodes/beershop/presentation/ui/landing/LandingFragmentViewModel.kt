package com.avicodes.beershop.presentation.ui.landing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avicodes.beershop.data.models.Beer
import com.avicodes.beershop.data.models.BeerItem
import com.avicodes.beershop.data.utils.Resource
import com.avicodes.beershop.domain.usecases.GetRandomBeerUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LandingFragmentViewModel(
    private val getRandomBeerUsecase: GetRandomBeerUsecase
): ViewModel() {

    private var _randomBeer : MutableLiveData<Resource<Beer>> = MutableLiveData()
    val randomBeer get() = _randomBeer

    fun getRandomBeer() = viewModelScope.launch(Dispatchers.IO) {
        _randomBeer.postValue(Resource.Loading())
        try {
            val beer = getRandomBeerUsecase.execute()
            _randomBeer.postValue(beer)
        } catch (e: Exception) {
            _randomBeer.postValue(Resource.Error(e.toString()))
        }
    }
}