package com.challenge.vegetablediscovery.mock

import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.domain.model.VegetableDetail
import com.challenge.vegetablediscovery.domain.model.Vitamin

object DomainModelMocks {

    val vegetable = Vegetable(id = 1L, name = "name", imageUrl = "imageUrl", mainVitamin = Vitamin.B12)
    val vegetableDetail = VegetableDetail(id = 1L, name = "name", description = "description", imageUrl = "imageUrl", mainVitamin = Vitamin.B12)
}