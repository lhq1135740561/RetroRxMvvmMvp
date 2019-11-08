package com.yunge.myretrofitrxlmvp.ui.ac

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yunge.myretrofitrxlmvp.R
import com.yunge.myretrofitrxlmvp.weamvvm.model.CastsBean
import com.yunge.myretrofitrxlmvp.weamvvm.model.ForecastsBean
import com.yunge.myretrofitrxlmvp.weamvvm.vmodel.WeatherViewModel
import com.yunge.myretrofitrxlmvp.weamvvm.vmodel.base.BaseViewModel
import com.yunge.myretrofitrxlmvp.ui.adapter.WeatherAdapter
import kotlinx.android.synthetic.main.activity_weather.*


class WeatherActivity : BaseActivity() {

    companion object {

        const val CITY_NAME = "南昌"

        const val TAG = "WeatherActivity"
    }

    private lateinit var weatherViewModel: WeatherViewModel

    private var castsBeanList = mutableListOf<CastsBean>()

    private val weatherAdapter = WeatherAdapter(castsBeanList,this)

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        weather_rv.layoutManager = LinearLayoutManager(this)
        weather_rv.adapter = weatherAdapter


        weather_srl.setColorSchemeResources(android.R.color.holo_orange_light)
        weather_srl.setOnRefreshListener {
            weatherViewModel.getWeather(CITY_NAME,this)
        }


    }

    /**
     * 接口方法先执行
     */
    override fun initViewModel(): BaseViewModel? {
        weatherViewModel = getViewModel(WeatherViewModel::class.java)
        weatherViewModel.forecastsBeanLiveData.observe(this, Observer {
            showWeather(it)

        })
        Log.d(TAG,"接口方法执行")
        return weatherViewModel
    }


    @SuppressLint("SetTextI18n")
    private fun showWeather(bean: ForecastsBean) {
        weather_text.text = bean.city
        //获取castsBeanList集合数据
        castsBeanList.clear()
        castsBeanList.addAll(bean.casts)
        weatherAdapter.notifyDataSetChanged()
        //更新数据成功
        weather_srl.isRefreshing = false

        //只有通过观察的数据得到的持有类，才有数据(onCreate()方法调用集合没有数据)
        if(castsBeanList.isNullOrEmpty()){
            Log.d(TAG,"集合为空")
        }else{
            castsBeanList.forEach {
                Log.d(TAG,"${it.daytemp}, ${it.dayweather}")
            }
        }
    }


    override fun onResume() {
        super.onResume()

        //获取城市天气数据
        weatherViewModel.getWeather(CITY_NAME,this)
        Log.d(TAG,"onResume")

    }

}
