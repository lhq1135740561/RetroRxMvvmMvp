package com.yunge.myretrofitrxlmvp.weamvvm.http.datasource.base

import android.annotation.SuppressLint
import android.content.Context
import com.yunge.myretrofitrxlmvp.weamvvm.http.BaseSubscriber
import com.yunge.myretrofitrxlmvp.weamvvm.http.ServerResultException
import com.yunge.myretrofitrxlmvp.weamvvm.http.ServiceCreator
import com.yunge.myretrofitrxlmvp.weamvvm.http.callback.RequestCallback
import com.yunge.myretrofitrxlmvp.weamvvm.http.config.HttpConfig
import com.yunge.myretrofitrxlmvp.weamvvm.http.model.OptionT
import com.yunge.myretrofitrxlmvp.weamvvm.http.model.BaseResponse
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

/**
 * 接口实现者
 * 具体的网络请求调用以及数据解析都只由BaseDataSource 来中转控制
 */
open class BaseDataSource {

//     val weatherService = ServiceCreator.createService(ApiService::class.java)


    //获取ServiceCreator中的ApiService接口方法
    protected fun <T : Any> getService(clz: Class<T>,host: String,context: Context): T{
        return ServiceCreator.getService(clz,host,context)
    }

    /**
     * 1.RequestCallback接口传入BaseSubscriber对象中(BaseSubscriber是Observer观察者类型)
     * 2.该方法用于BaseSubscriber与RequestCallback连接
     * 3.具体的网络请求调用以及数据解析由 executeQuietly()方法 来中转控制
     */
    protected fun <T> executeQuietly(observable: Observable<BaseResponse<T>>,callback: RequestCallback<T>){
        execute(observable,BaseSubscriber(callback))
    }


    //网络请求调用以及数据解析
    @SuppressLint("CheckResult")
    fun <T>execute(observable: Observable<BaseResponse<T>>, observer: Observer<OptionT<T>>) {

            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(Function<BaseResponse<T>, ObservableSource<OptionT<T>>> {
                    when {
                        it.code == HttpConfig.CODE_SUCCESS || it.message == "OK" -> {
                            val optional: OptionT<T> = OptionT(it.data)
                            return@Function createData(optional)
                        }
                        else -> {
                            throw ServerResultException(it.message ?: "未知错误", it.code)
                        }
                    }
                }).subscribeWith(observer)

    }


    //创建Observable(T 泛型类型代表 BaseResponse<List<ForecastsBean>>)
    private fun <T> createData(t: T): Observable<T> {

        return Observable.create {emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            }catch (e: Exception){
                emitter.onError(e)
            }
        }
    }
}