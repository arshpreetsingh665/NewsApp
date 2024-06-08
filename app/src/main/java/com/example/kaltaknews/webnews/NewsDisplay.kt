package com.example.kaltaknews.webnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.kaltaknews.R
import com.example.kaltaknews.databinding.ActivityWebNewsBinding

class NewsDisplay : AppCompatActivity() {
    var mbinding:ActivityWebNewsBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding=DataBindingUtil.setContentView(this,R.layout.activity_web_news)

                // Retrieve data from the intent's extras bundle
                val bundle = intent.extras
                if (bundle != null) {
                    val newsTitle = bundle.getString("newsTitle")
                    val newsImage = bundle.getString("newsImage")
                    val newsContent = bundle.getString("newsContent")
                    val newsPublishAt = bundle.getString("newsPublishAt")
                    val newsAuthor = bundle.getString("newsAuthor")

                    // Use the retrieved data to populate the UI or perform any other operations
                    // For example, set the title, content, and image using TextView and ImageView

                    // Example usage:

                    mbinding?.titleForDisplay?.text = newsTitle


                    mbinding?.contentForDisplay?.text = newsContent


                    // Load and display the news image using an image loading library like Glide or Picasso
                    // Example using Glide:
                    Glide.with(this)
                        .load(newsImage)
                        .into(mbinding?.imageviewForDisplay!!)
                }
            }
        }



