package com.yunge.myretrofitrxlmvp.newsmvp.http.callback

import com.yunge.myretrofitrxlmvp.newsmvp.http.model.NewsClass
import java.lang.Exception

interface RequestCallback<T> {

    fun onSuccess(data: NewsClass<T>)

}

interface RequestFailCallback<T>: RequestCallback<T>{

    fun onFail(e: Exception)
}