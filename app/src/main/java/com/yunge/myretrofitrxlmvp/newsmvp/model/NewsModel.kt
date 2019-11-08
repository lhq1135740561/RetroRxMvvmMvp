package com.yunge.myretrofitrxlmvp.newsmvp.model

import android.annotation.SuppressLint
import com.yunge.myretrofitrxlmvp.newsmvp.http.BaseSubscriber
import com.yunge.myretrofitrxlmvp.newsmvp.http.ServiceCreator
import com.yunge.myretrofitrxlmvp.newsmvp.http.callback.RequestCallback
import com.yunge.myretrofitrxlmvp.newsmvp.http.config.HttpConfig
import com.yunge.myretrofitrxlmvp.newsmvp.http.model.NewsClass
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

open class NewsModel {   // : Imodel {


    //获取Api接口对象
    protected fun <T : Any> getService(clazz: Class<T>, url: String): T {
        return ServiceCreator.createService(clazz, url)
    }

    //执行网络请求的中转控制
    protected fun <T> executeQuery(
        observable: Observable<NewsClass<T>>,
        callback: RequestCallback<T>
    ) {
        execute(observable, BaseSubscriber(callback))  //回调接口(返回onSuccess()和onFail()方法)
    }


    //网络请求数据,并解析数据
    @SuppressLint("CheckResult")
    private fun <T> execute(observable: Observable<NewsClass<T>>, observer: Observer<NewsClass<T>>) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(Function<NewsClass<T>,ObservableSource<NewsClass<T>>>{
                when{
                    it.info == HttpConfig.NEWS_INFO && it.status == HttpConfig.NEWS_STATUS ->
                        return@Function createObservable(it)
                    else -> throw Exception()
                }
            }).subscribe(observer)
    }

    //创建Observable对象
    private fun <T> createObservable(t: T): Observable<T> {
        return Observable.create { emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }

        }
    }
}