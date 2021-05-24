package com.challenge.vegetablediscovery.ui.vegetabledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.repository.VegetableRepository
import com.challenge.vegetablediscovery.repository.VegetableRepositoryImpl
import kotlinx.coroutines.flow.map

class VegetableDetailViewModel : ViewModel() {

    private val vegetableRepository: VegetableRepository by lazy { VegetableRepositoryImpl() }

    fun getVegetableDetail(vegetableId: Long): LiveData<Vegetable?> =
        vegetableRepository.getVegetableList().map { it.find { it.id == vegetableId } }.asLiveData()
}