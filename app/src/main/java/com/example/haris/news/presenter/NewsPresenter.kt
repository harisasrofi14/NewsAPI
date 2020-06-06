package com.example.haris.news.presenter

import com.example.haris.news.network.ApiRepository
import com.example.haris.news.network.NewsApi
import com.example.haris.news.NewsView
import com.example.haris.news.model.NewsResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.google.gson.Gson

class NewsPresenter(private val view: NewsView) {
    private val gson = Gson()
    private val apiRepository = ApiRepository()

    fun getNewsResult(PageSize: Int, Page: Int) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val data = gson.fromJson(
                    apiRepository
                        .doRequestAsync(NewsApi.getEverything(PageSize, Page)).await(),
                    NewsResult::class.java
                )
                if (data.articles.isNotEmpty()) {
                    view.showNewsResult(data.articles)
                } else {
                    view.onErrorGetNews()
                }
            } catch (e: Exception) {
                view.onErrorGetNews()
            }

        }
    }

}