package com.yunge.myretrofitrxlmvp.newsmvp.model

import com.yunge.myretrofitrxlmvp.newsmvp.http.model.NewsClass
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * 获取gson解析后的数据
 */

interface Imodel {

    fun <T> getJson(observable: Observable<NewsClass<T>>, observer: Observer<NewsClass<T>>)
}
