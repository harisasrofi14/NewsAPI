package com.example.haris.news

import com.example.haris.news.model.Article

interface NewsView {
    fun showLoading()
    fun hideLoading()
    fun showNewsResult(data: List<Article>)
    fun onErrorGetNews()
}