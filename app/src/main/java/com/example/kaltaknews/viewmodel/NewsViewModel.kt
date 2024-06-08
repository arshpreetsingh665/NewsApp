package com.example.kaltaknews.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kaltaknews.modelclass.News
import com.example.kaltaknews.newrepo.NewsRepository
import retrofit2.Call

class NewsViewModel():ViewModel() {

    var newsRepository=NewsRepository()
      suspend fun getHeadlines(country:String, category:String):Call<News>{
         return newsRepository.getHeadlines(country, category)
             }
}