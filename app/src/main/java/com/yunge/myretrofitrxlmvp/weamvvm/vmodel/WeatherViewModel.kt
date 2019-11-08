package com.yunge.myretrofitrxlmvp.weamvvm.vmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.yunge.myretrofitrxlmvp.MainApplication
import com.yunge.myretrofitrxlmvp.weamvvm.http.callback.RequestCallback
import com.yunge.myretrofitrxlmvp.weamvvm.http.datasource.WeatherDataSource
import com.yunge.myretrofitrxlmvp.weamvvm.model.ForecastsBean
import com.yunge.myretrofitrxlmvp.weamvvm.vmodel.base.BaseViewModel

class WeatherViewModel : BaseViewModel() {

    private val weatherDataSource = WeatherDataSource()

    val forecastsBeanLiveData = MutableLiveData<ForecastsBean>()

    fun getWeather(city: String,context: Context){
        weatherDataSource.queryWeather(city,object : RequestCallback<List<ForecastsBean>>{
            override fun onSuccess(data: List<ForecastsBean>) {
                if(data.isNotEmpty()) {
                    forecastsBeanLiveData.value = data[0]
                    Toast.makeText(MainApplication.context,"数据解析成功",Toast.LENGTH_SHORT).show()
                }
            }

        },context)
    }
}