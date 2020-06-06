package com.example.haris.news.network

import com.example.haris.news.BuildConfig

object NewsApi {

    fun getEverything(PageSize: Int, Page: Int): String {
        return BuildConfig.BASE_URL + "everything?q=Corona" + "&pageSize=" + PageSize + "&page=" + Page + "&apiKey=" + BuildConfig.API_KEY
    }
}