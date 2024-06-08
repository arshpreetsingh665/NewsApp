package com.example.kaltaknews.newrepo

import com.example.kaltaknews.apiutil.RetrofitImplementation
import com.example.kaltaknews.modelclass.News
import retrofit2.Call

class NewsRepository {
    suspend fun getHeadlines(country:String,category:String): Call<News> {
        return RetrofitImplementation.getInstance().getHeadlines(country,category)



   }
}