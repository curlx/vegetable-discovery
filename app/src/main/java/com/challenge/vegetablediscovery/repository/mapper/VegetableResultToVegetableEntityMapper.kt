package com.challenge.vegetablediscovery.repository.mapper

import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.data.entities.VegetableEntity
import com.challenge.vegetablediscovery.domain.model.Vitamin

class VegetableResultToVegetableEntityMapper : Mapper<VegetableResult, VegetableEntity?> {

    override fun map(input: VegetableResult): VegetableEntity? =
        input.run {
            if (id != null && name != null && imageUrl != null) {
                VegetableEntity(id = id, name = name, description = description ?: "", imageUrl = imageUrl, mainVitamin = mapMainVitamin(mainVitamin))
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