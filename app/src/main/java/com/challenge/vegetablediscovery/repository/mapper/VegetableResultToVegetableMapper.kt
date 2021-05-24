package com.challenge.vegetablediscovery.repository.mapper

import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.domain.model.Vegetable

class VegetableResultToVegetableMapper : Mapper<VegetableResult, Vegetable?> {

    override fun map(input: VegetableResult): Vegetable? =
        input.run {
            if (id != null && name != null && description != null) {
                Vegetable(id = id, name = name, description = description, imageUrl = imageUrl ?: "")
            } else {
                null
            }
        }
}