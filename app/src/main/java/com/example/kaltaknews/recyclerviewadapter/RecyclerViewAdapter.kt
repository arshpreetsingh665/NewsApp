package com.example.kaltaknews.recyclerviewadapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.kaltaknews.databinding.ItemViewBinding
import com.example.kaltaknews.modelclass.Article
import com.example.kaltaknews.webnews.NewsDisplay


class RecyclerViewAdapter(var context: Context, var articles: List<Article>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val article: MutableList<Article> = mutableListOf()

    class ViewHolder(var itemViewBinding: ItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Article>) {
        article.clear()
        article.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = articles[position]
        Glide.with(holder.itemView.context).load(news.urlToImage)
            .into(holder.itemViewBinding.NewsDisplayImage)
        holder.itemViewBinding.Title.text = news.title
        holder.itemViewBinding.content.text = news.content
        holder.itemViewBinding.AuthorName.text = news.author
        holder.itemViewBinding.Publishedat.text = news.publishedAt



        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, NewsDisplay::class.java)
            val bundle = Bundle()
            bundle.putString("newsTitle", news.title)
            bundle.putString("newsImage", news.urlToImage)
            bundle.putString("newsContent", news.content)
            bundle.putString("newsPublishAt", news.publishedAt)
            bundle.putString("newsAuthor", news.author)
            //  bundle.putString("newsContent", news.content)
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)
        }


    }

}
