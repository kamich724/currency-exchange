package com.example.currencyexchanger.api

import com.example.currencyexchanger.api.entities.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("api/latest.json")
    suspend fun getExchangeRates(@Query("app_id") appId: String="48ebf7e6b84c49938d197f9287ea387c") : ExchangeResponse

    @GET("api/currencies.json")
   suspend fun getCurrenciesList(@Query("app_id") appId: String="48ebf7e6b84c49938d197f9287ea387c") : Map<String, String>
}