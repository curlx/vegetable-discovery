package com.challenge.vegetablediscovery.ui.vegetablelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.challenge.vegetablediscovery.base.Event
import com.challenge.vegetablediscovery.domain.model.UpdateVegetableListResult
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.repository.VegetableRepository
import com.challenge.vegetablediscovery.repository.VegetableRepositoryImpl
import kotlinx.coroutines.launch

class VegetableListViewModel : ViewModel() {

    private val vegetableRepository: VegetableRepository by lazy { VegetableRepositoryImpl() }

    private val _statusMessage: MutableLiveData<Event<String>> = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>> = _statusMessage

    private val _isRefreshing: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    fun getVegetableList(): LiveData<List<Vegetable>> =
        vegetableRepository.getVegetableList().asLiveData()

    fun refreshVegetableList() {
        viewModelScope.launch {
            val result = vegetableRepository.updateVegetableList()
            when (result) {
                // TODO: use string resource
                UpdateVegetableListResult.Success -> "\uD83E\uDD51 Vegetable information updated"
                is UpdateVegetableListResult.Failed -> result.message
            }.also {
                _statusMessage.value = Event(it)
            }
            _isRefreshing.value = false
        }
    }

}