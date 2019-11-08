package com.yunge.myretrofitrxlmvp.newsmvp.http.service

import com.yunge.myretrofitrxlmvp.newsmvp.http.model.NewsClass
import com.yunge.myretrofitrxlmvp.newsmvp.model.NewsBeanData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //新闻接口
    @GET("AppNews/getNewsList/type/1/p/{page}")   //@Path(接口里面只有参数值没有参数名称的使用方法)
    fun queryNews(@Path("page") page: Int): Observable<NewsClass<List<NewsBeanData>>>
}