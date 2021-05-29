package com.challenge.vegetablediscovery.repository.mapper

import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.domain.model.Vitamin

class VegetableResultToVegetableMapper : Mapper<VegetableResult, Vegetable?> {

    override fun map(input: VegetableResult): Vegetable? =
        input.run {
            if (id != null && name != null && imageUrl != null) {
                Vegetable(id = id, name = name, description = description ?: "", imageUrl = imageUrl, mainVitamin = mapMainVitamin(mainVitamin))
            } else {
                null
            }
        }

    private fun mapMainVitamin(mainVitamin: String?) =
        mainVitamin?.let {
            try {
                Vitamin.valueOf(it)
            } catch(ignored: Exception) { Vitamin.UNKNOWN }
        } ?: Vitamin.UNKNOWN
}