package com.challenge.vegetablediscovery.api

import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface VegetableApi {

    @GET("vegetableList")
    suspend fun fetchVegetableList(): List<VegetableResult>

    companion object {
        fun create(baseUrl: String): VegetableApi {
            val client = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VegetableApi::class.java)
        }
    }
}