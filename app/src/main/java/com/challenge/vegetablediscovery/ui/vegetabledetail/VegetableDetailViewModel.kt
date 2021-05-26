package com.challenge.vegetablediscovery.ui.vegetabledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.repository.VegetableRepository

class VegetableDetailViewModel(
    private val vegetableRepository: VegetableRepository
) : ViewModel() {

    fun getVegetableDetail(vegetableId: Long): LiveData<Vegetable?> =
        vegetableRepository.getVegetable(vegetableId).asLiveData()
}