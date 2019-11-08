package com.yunge.myretrofitrxlmvp.weamvvm.http.interceptor

import android.text.TextUtils
import com.yunge.myretrofitrxlmvp.weamvvm.http.config.HttpConfig
import okhttp3.Interceptor
import okhttp3.Response

class FilterInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        //获取httpBuilder
        val httpBuilder = request.url().newBuilder()

        //获取Heathers
        val headers = request.headers()
        if (headers.size() > 0){
            val requestType = headers.get(HttpConfig.HTTP_REQUEST_TYPE_KEY)
            if (!TextUtils.isEmpty(requestType)){
                when(requestType){
                    HttpConfig.HTTP_REQUEST_WEATHER ->
                        //添加参数 Key值
                        httpBuilder.addEncodedQueryParameter(HttpConfig.KEY,HttpConfig.MY_KEY)
                }
            }
        }
        //获取requestBuilder对象
        val requestBuilder = request.newBuilder().url(httpBuilder.build())
     return  chain.proceed(requestBuilder.build())
    }
}