package com.yunge.myretrofitrxlmvp.newsmvp.http.config

class HttpConfig {


    companion object {

        const val READ_TIMEOUT = 10000L
        const val WRITE_TIMEOUT = 10000L
        const val CONNECT_TIMEOUT = 10000L


        const val NEWS_INFO = "获取内容成功"
        const val NEWS_STATUS = 1

        const val NEWS_BASE_URL = "http://api.expoon.com/"
    }
}