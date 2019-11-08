package com.yunge.myretrofitrxlmvp.newsmvp.http

import android.widget.Toast
import com.yunge.myretrofitrxlmvp.MainApplication
import com.yunge.myretrofitrxlmvp.newsmvp.http.callback.RequestCallback
import com.yunge.myretrofitrxlmvp.newsmvp.http.callback.RequestFailCallback
import com.yunge.myretrofitrxlmvp.newsmvp.http.model.NewsClass
import io.reactivex.observers.DisposableObserver
import java.lang.Exception

class BaseSubscriber<T>(var callback: RequestCallback<T>)
    : DisposableObserver<NewsClass<T>>() {

    override fun onComplete() {

    }

    override fun onNext(t: NewsClass<T>) {
        callback.onSuccess(t)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        if (callback is RequestFailCallback){
            if (e is Exception) {
                (callback as RequestFailCallback<T>).onFail(e)
            }
        } else{
            Toast.makeText(MainApplication.context,"网络请求失败",Toast.LENGTH_SHORT).show()
        }
    }
}