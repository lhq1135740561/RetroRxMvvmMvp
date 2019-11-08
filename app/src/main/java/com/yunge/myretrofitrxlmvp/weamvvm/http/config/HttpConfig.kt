package com.yunge.myretrofitrxlmvp.weamvvm.http.config

class HttpConfig {

    companion object {


        const val READ_TIMEOUT = 10000L
        const val WRITE_TIMEOUT = 10000L
        const val CONNECT_TIMEOUT = 10000L

        const val CODE_SUCCESS = 10000

        const val CODE_UNKNOWN = -1024

        //接口类型
        const val HTTP_REQUEST_TYPE_KEY = "requestType"

        const val KEY = "key"

        //Weather天气
        const val BASE_URL_WEATHER = "https://restapi.amap.com/v3/"

        const val MY_KEY = "50286a09fc29d9aa0fd9b92e3cbb75a2"

        const val HTTP_REQUEST_WEATHER = "weather"


        //新闻
        const val BASE_URL_NEWS = "http://api.expoon.com/"


        //http://api.expoon.com/AppNews/getNewsList/type/1/p/1


    }

}