package com.yunge.myretrofitrxlmvp.newsmvp.model

import com.google.gson.annotations.SerializedName

class NewsBeanData(var news_id: String,
                   @SerializedName("news_title") var title: String,
                   @SerializedName("news_summary") var summary: String,
                   @SerializedName("pic_url") var url: String)