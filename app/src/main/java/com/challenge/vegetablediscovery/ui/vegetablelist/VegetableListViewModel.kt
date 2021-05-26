package com.challenge.vegetablediscovery.ui.vegetablelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.challenge.vegetablediscovery.base.Event
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.repository.VegetableRepository
import com.challenge.vegetablediscovery.repository.VegetableRepositoryImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class VegetableListViewModel : ViewModel() {

    private val vegetableRepository: VegetableRepository by lazy { VegetableRepositoryImpl() }

    private val _statusMessage: MutableLiveData<Event<String>> = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>> = _statusMessage

    private val _isRefreshing: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    val vegetables: LiveData<List<Vegetable>> = vegetableRepository.getVegetableList().asLiveData()

    init {
        refreshVegetableCache()
    }

    fun refreshVegetableCache() {
        launchDataLoad { vegetableRepository.refreshVegetableCache() }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                block()
                _statusMessage.value = Event("\uD83E\uDD51 Vegetable information updated")
            } catch (error: Throwable) {
                // TODO: use user friendly message and add a log
                _statusMessage.value = Event(error.toString())
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}