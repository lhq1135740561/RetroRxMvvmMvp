package com.yunge.myretrofitrxlmvp.newsmvp.http.model


/**
 * 定义一个泛型数据类
 */
class NewsClass<T>(

    var status: Int = 1,

    var info: String? = null,

    var data: T
)