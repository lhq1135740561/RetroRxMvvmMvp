package com.yunge.myretrofitrxlmvp.newsmvp.http

import com.yunge.myretrofitrxlmvp.newsmvp.http.config.HttpConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceCreator {

    /**
     * 创建Retrofit对象
     */
    private fun createRetrofit(url: String): Retrofit{
        val builder = OkHttpClient.Builder()
            .readTimeout(HttpConfig.READ_TIMEOUT,TimeUnit.SECONDS)
            .writeTimeout(HttpConfig.WRITE_TIMEOUT,TimeUnit.SECONDS)
            .connectTimeout(HttpConfig.CONNECT_TIMEOUT,TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        val client = builder.build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    /**
     * 创建接口对象(接口定义成泛型)
     */
    fun <T : Any> createService(clz: Class<T>,url: String): T{
        return createRetrofit(url).create(clz)
    }
}