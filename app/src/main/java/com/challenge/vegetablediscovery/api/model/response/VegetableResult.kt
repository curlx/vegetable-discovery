package com.challenge.vegetablediscovery.api.model.response

import com.google.gson.annotations.SerializedName

data class VegetableResult(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null
)