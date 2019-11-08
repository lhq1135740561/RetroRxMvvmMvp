package com.yunge.myretrofitrxlmvp.weamvvm.http.model

import com.google.gson.annotations.SerializedName

class NewsData(val status: Int,val data: List<DataBean>){

    class DataBean(@SerializedName("news_title") val title: String,
                   @SerializedName("news_summary") val summary: String,
                   @SerializedName("pic_url") val url: String)

}

