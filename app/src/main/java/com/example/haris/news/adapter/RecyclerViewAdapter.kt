package com.example.haris.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.haris.news.R
import com.example.haris.news.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*


class RecyclerViewAdapter(private  val items : List<Article>, private val clickListener: (Article)-> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.item_news,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindResult(items[position],clickListener)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivThumbnail = view.iv_thumbnail
        private val tvTitle = view.tvTitle


        fun bindResult (result: Article,clickListener: (Article) -> Unit ) {
            Picasso.get().load(result.urlToImage).into(ivThumbnail)
            tvTitle.text = result.title
            itemView.setOnClickListener{clickListener(result)}

        }
    }
}