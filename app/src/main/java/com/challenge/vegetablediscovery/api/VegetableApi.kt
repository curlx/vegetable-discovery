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
        private const val BASE_URL = "https://europe-west2-vegetable-discovery.cloudfunctions.net/"

        fun create(): VegetableApi {
            val client = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VegetableApi::class.java)
        }
    }
}