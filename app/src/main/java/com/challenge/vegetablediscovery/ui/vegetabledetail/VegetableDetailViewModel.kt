package com.challenge.vegetablediscovery.ui.vegetabledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.challenge.vegetablediscovery.domain.model.VegetableDetail
import com.challenge.vegetablediscovery.domain.contract.VegetableRepository

class VegetableDetailViewModel(
    private val vegetableRepository: VegetableRepository
) : ViewModel() {

    fun getVegetableDetail(vegetableId: Long): LiveData<VegetableDetail?> =
        vegetableRepository.getVegetable(vegetableId).asLiveData()
}