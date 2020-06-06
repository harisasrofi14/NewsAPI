package com.example.haris.news

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.haris.news.presenter.NewsPresenter
import com.example.haris.news.adapter.RecyclerViewAdapter
import com.example.haris.news.model.Article
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NewsView {

    private var newsItems: MutableList<Article> = mutableListOf()
    private var page = 0
    private var pageSize = 10
    private lateinit var presenter: NewsPresenter
    private lateinit var manager: LinearLayoutManager
    private val newsAdapter =
        RecyclerViewAdapter(newsItems) { item: Article ->
            itemClicked(item)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = LinearLayoutManager(this)
        rv_news.layoutManager = manager
        rv_news.adapter = newsAdapter
        page = 1;
        presenter = NewsPresenter(this)
        presenter.getNewsResult(pageSize, page)

        rv_news.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && dy != 0) {
                    presenter.getNewsResult(pageSize, page)
                }
            }
        })
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showNewsResult(data: List<Article>) {
        newsItems.addAll(data)
        newsAdapter.notifyDataSetChanged()
        page += 1
    }

    override fun onErrorGetNews() {
        progressBar.visibility = View.GONE
        Toast.makeText(this@MainActivity, "Error While Getting Data", Toast.LENGTH_SHORT).show()
    }

    private fun itemClicked(item: Article) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }


}
