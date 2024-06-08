package com.example.kaltaknews.apiinterface

import com.example.kaltaknews.modelclass.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "c1c27056ae3440338e2eacdb978ac09d"

interface ApiServices {

    // https://newsapi.org/v2/top-headlines?apiKey=c1c27056ae3440338e2eacdb978ac09d


    @GET("v2/top-headlines?apiKey=$API_KEY")
      fun getHeadlines(
        @Query("country") country: String,
        @Query("category") category: String
    ): Call<News>
}