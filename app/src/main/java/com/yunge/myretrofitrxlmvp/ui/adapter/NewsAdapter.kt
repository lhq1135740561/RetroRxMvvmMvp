package com.yunge.myretrofitrxlmvp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yunge.myretrofitrxlmvp.R
import com.yunge.myretrofitrxlmvp.newsmvp.model.NewsBeanData

class NewsAdapter(private val newsBeanDataList: List<NewsBeanData>,
                  private val listener: OnClickNewLisetener,
                  var context: Context)
    : RecyclerView.Adapter<NewsAdapter.NewsViewholder>() {

    interface OnClickNewLisetener{

        fun onItemClick(page: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewholder {
        return NewsViewholder(LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false))
    }

    override fun getItemCount(): Int {
        return newsBeanDataList.size
    }

    override fun onBindViewHolder(holder: NewsViewholder, position: Int) {
        val newsBeanData = newsBeanDataList[position]

        Glide.with(context).load(newsBeanData.url).error(R.mipmap.ic_mei_ripple).into(holder.news_item_image)
        holder.news_item_title.text = newsBeanData.title
        holder.news_item_summary.text = newsBeanData.summary

        holder.itemView.setOnClickListener {
            //接口回调跳转页面
            listener.onItemClick(position)
        }
    }

    class NewsViewholder(itemView: View): RecyclerView.ViewHolder(itemView){
        var news_item_image: ImageView = itemView.findViewById(R.id.news_item_image)
        var news_item_title: TextView = itemView.findViewById(R.id.news_item_layout_title)
        var news_item_summary: TextView = itemView.findViewById(R.id.news_item_layout_summary)
    }
}