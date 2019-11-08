package com.yunge.myretrofitrxlmvp.weamvvm.http

import android.content.Context
import com.yunge.myretrofitrxlmvp.weamvvm.http.config.HttpConfig
import com.yunge.myretrofitrxlmvp.weamvvm.http.interceptor.FilterInterceptor
import leavesc.hello.monitor.MonitorInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 创建retrofit对象和创建接口对象
 */
object ServiceCreator {

    //创建Retrofit对象
    private fun createRetrofit(url: String,context: Context): Retrofit{
        val builder = OkHttpClient.Builder()
            .readTimeout(HttpConfig.READ_TIMEOUT,TimeUnit.SECONDS)
            .writeTimeout(HttpConfig.WRITE_TIMEOUT,TimeUnit.SECONDS)
            .connectTimeout(HttpConfig.CONNECT_TIMEOUT,TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(FilterInterceptor())
            .addInterceptor(MonitorInterceptor(context))

        val client = builder.build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    /**
     * 创建接口对象(接口定义成泛型类型)
     */
//    fun <T> createService(serviceClass: Class<T>): T = createRetrofit().create(serviceClass)

    fun <T : Any> getService(clz: Class<T>, host: String,context: Context): T {

        return createRetrofit(host,context).create(clz)
    }
}