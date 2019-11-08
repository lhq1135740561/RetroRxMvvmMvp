package com.yunge.myretrofitrxlmvp.weamvvm.http.datasource

import android.content.Context
import com.yunge.myretrofitrxlmvp.weamvvm.http.api.ApiService
import com.yunge.myretrofitrxlmvp.weamvvm.http.callback.RequestCallback
import com.yunge.myretrofitrxlmvp.weamvvm.http.config.HttpConfig
import com.yunge.myretrofitrxlmvp.weamvvm.http.datasource.base.BaseDataSource
import com.yunge.myretrofitrxlmvp.weamvvm.model.ForecastsBean

class WeatherDataSource : BaseDataSource(){

    fun queryWeather(city: String,callback: RequestCallback<List<ForecastsBean>>,context: Context){

        //网络请求调用以及数据解析
        //Observable被观察者对象传入，T泛型类型被具体类型替代
        executeQuietly(getService(ApiService::class.java,HttpConfig.BASE_URL_WEATHER,context).queryWeather(city),callback)

    }

}


