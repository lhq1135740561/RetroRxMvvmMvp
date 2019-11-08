package com.yunge.myretrofitrxlmvp.weamvvm.http.callback

import com.yunge.myretrofitrxlmvp.weamvvm.http.BaseException

interface RequestCallback<T> {

    fun onSuccess(data: T)

}

interface RequestMultiplyCallback<T> : RequestCallback<T> {

    fun onFail(e: BaseException)  //请求失败

}