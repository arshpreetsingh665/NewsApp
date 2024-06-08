package com.example.kaltaknews.Science

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kaltaknews.databinding.FragmentScienceBinding
import com.example.kaltaknews.modelclass.News
import com.example.kaltaknews.recyclerviewadapter.RecyclerViewAdapter
import com.example.kaltaknews.viewmodel.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScienceFragment : Fragment() {
    var mbinding: FragmentScienceBinding? = null
    val binding get() = mbinding!!
    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       mbinding = FragmentScienceBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        getNews()
        return binding.root
    }


    fun getNews() {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.getHeadlines("in","science").enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if (news != null) {
                        Log.e("Success", news.toString())
                    }
                    mbinding?.scienceRecyclerView?.layoutManager =
                        LinearLayoutManager(view?.context)
                    mbinding?.scienceRecyclerView?.adapter =
                        RecyclerViewAdapter(view?.context!!, news!!.articles)
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.e("Error", t.toString())
                }

            })

        }
    }}