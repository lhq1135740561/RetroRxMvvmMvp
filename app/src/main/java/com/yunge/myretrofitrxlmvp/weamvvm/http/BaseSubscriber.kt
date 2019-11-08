package com.yunge.myretrofitrxlmvp.weamvvm.http

import com.yunge.myretrofitrxlmvp.weamvvm.holder.ToastHolder
import com.yunge.myretrofitrxlmvp.weamvvm.http.callback.RequestCallback
import com.yunge.myretrofitrxlmvp.weamvvm.http.callback.RequestMultiplyCallback
import com.yunge.myretrofitrxlmvp.weamvvm.http.model.OptionT
import io.reactivex.observers.DisposableObserver

class BaseSubscriber<T> constructor(private val callback:RequestCallback<T>):
    DisposableObserver<OptionT<T>>() {

    override fun onNext(t: OptionT<T>) {

        callback.onSuccess(t.value)
    }

    override fun onComplete() {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        val msg = e.message ?: "未知错误"
        if (callback is RequestMultiplyCallback){
            if (e is BaseException){
                callback.onFail(e)
            }else{
                callback.onFail(ServerResultException(msg))
            }
        }else {
            ToastHolder.setMessage(msg)
        }

    }
}