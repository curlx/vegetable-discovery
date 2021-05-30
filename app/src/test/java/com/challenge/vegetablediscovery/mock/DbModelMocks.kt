package com.challenge.vegetablediscovery.mock

import com.challenge.vegetablediscovery.data.entities.VegetableEntity
import com.challenge.vegetablediscovery.domain.model.Vitamin

object DbModelMocks {

    val vegetableEntity = VegetableEntity(id = 1L, name = "name", description = "description", imageUrl = "imageUrl", mainVitamin = Vitamin.B12)
}