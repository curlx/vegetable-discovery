package com.challenge.vegetablediscovery.mock

import com.challenge.vegetablediscovery.api.model.response.VegetableResult

object NetworkModelMocks {

    val vegetableResult = VegetableResult(id = 1L, name = "name", description = "description", imageUrl = "imageUrl", mainVitamin = "B12",
        originCountryCode = "JP")
}