package com.example.haris.news.network

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRepository {

    fun doRequestAsync(url: String): Deferred<String> {
        return GlobalScope.async {
            URL(url).readText()
        }
    }
}