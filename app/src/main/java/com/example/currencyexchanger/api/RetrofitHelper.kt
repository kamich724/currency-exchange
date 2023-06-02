package com.example.currencyexchanger.api

import androidx.databinding.ktx.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    const val baseUrl = "https://openexchangerates.org/"

    fun getInstance(): Retrofit {
        val gson= GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getClient())
            .build()
    }

    private fun getClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.readTimeout(2, TimeUnit.MINUTES)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(2, TimeUnit.MINUTES)


        if (BuildConfig.DEBUG) {
            builder.addInterceptor(logging)
        }
        return builder.build()
    }

}