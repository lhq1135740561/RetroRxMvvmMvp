package com.yunge.myretrofitrxlmvp.weamvvm.http.api

import com.yunge.myretrofitrxlmvp.weamvvm.http.config.HttpConfig
import com.yunge.myretrofitrxlmvp.weamvvm.http.model.*
import com.yunge.myretrofitrxlmvp.weamvvm.model.ForecastsBean

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    //定义天气接口
    @Headers(HttpConfig.HTTP_REQUEST_TYPE_KEY+ ":" + HttpConfig.HTTP_REQUEST_WEATHER)
    @GET("weather/weatherInfo?extensions=all")
    fun queryWeather(@Query("city") city: String): Observable<BaseResponse<List<ForecastsBean>>>


    //定义新闻接口
    fun queryNews(): Observable<NewsData>
}