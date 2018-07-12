package com.example.ujap.Adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ujap.Model.News
import com.example.ujap.R
import com.example.ujap.Utilities.colors
import kotlinx.android.synthetic.main.new_grid_item.view.*
import java.util.*

class NewsAdapter(val context: Context, val news: List<News>) : RecyclerView.Adapter<NewsAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_grid_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return news.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindNews(news[position], context)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsTitle = itemView.findViewById<TextView>(R.id.newsTitleTxt)
        val newsBody = itemView.findViewById<TextView>(R.id.newsBodyTxt)
        fun bindNews(news: News, context: Context) {
            val random = Random()
            val colorNumber = random.nextInt(3)
            val color = colors[colorNumber]
            newsTitle.setBackgroundColor(color)
            newsTitle.text = news.title
            newsBody.text = news.body
            val gd = GradientDrawable()
            gd.setStroke(3, color)
            newsBody.background = gd
            if (colorNumber != 0) newsTitle.setTextColor(Color.WHITE)
        }
    }
}