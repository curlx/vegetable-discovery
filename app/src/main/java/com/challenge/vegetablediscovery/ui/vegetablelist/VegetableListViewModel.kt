package com.challenge.vegetablediscovery.ui.vegetablelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.challenge.vegetablediscovery.domain.model.UpdateVegetableListResult
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.repository.VegetableRepository
import com.challenge.vegetablediscovery.repository.VegetableRepositoryImpl

class VegetableListViewModel : ViewModel() {

    private val vegetableRepository: VegetableRepository by lazy { VegetableRepositoryImpl() }

    fun getVegetableList(): LiveData<List<Vegetable>> =
        vegetableRepository.getVegetableList().asLiveData()

    suspend fun updateVegetableList(): UpdateVegetableListResult =
        vegetableRepository.updateVegetableList()

}