package com.example.kaltaknews.apiutil

import com.example.kaltaknews.apiinterface.ApiServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation {
    companion object {
        fun getInstance(): ApiServices {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            return Retrofit.Builder().baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build())
                .build().create(
                    ApiServices::class.java
                )
        }
    }
}