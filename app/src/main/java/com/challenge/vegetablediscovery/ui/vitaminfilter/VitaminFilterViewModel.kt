package com.challenge.vegetablediscovery.ui.vitaminfilter

import androidx.lifecycle.ViewModel
import com.challenge.vegetablediscovery.domain.model.Vitamin

class VitaminFilterViewModel : ViewModel() {

    val vitaminFilters: List<Vitamin> by lazy {
        Vitamin.values()
            .filterNot { it == Vitamin.UNKNOWN || it == Vitamin.ALL }
            .toMutableList().apply {
                add(0, Vitamin.ALL)
            }
    }
}