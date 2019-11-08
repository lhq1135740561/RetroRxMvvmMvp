package com.yunge.myretrofitrxlmvp.newsmvp.presenter

import com.yunge.myretrofitrxlmvp.newsmvp.http.ServiceCreator
import com.yunge.myretrofitrxlmvp.newsmvp.http.callback.RequestCallback
import com.yunge.myretrofitrxlmvp.newsmvp.http.config.HttpConfig
import com.yunge.myretrofitrxlmvp.newsmvp.http.model.NewsClass
import com.yunge.myretrofitrxlmvp.newsmvp.model.NewsBeanData
import com.yunge.myretrofitrxlmvp.newsmvp.model.NewsModel
import com.yunge.myretrofitrxlmvp.newsmvp.view.IView
import com.yunge.myretrofitrxlmvp.newsmvp.http.service.ApiService

/**
 * 将泛型类型转换成具体类的类型 (充当View层)
 */
class NewsPresenter : NewsModel(){  //: RequestCallback<List<NewsBeanData>>{


//    override fun onSuccess(data: NewsClass<List<NewsBeanData>>) {
//        iview.setNews(data.data)
//    }

    fun setJson(newsPage: Int,callback: RequestCallback<List<NewsBeanData>>){
        //从中转控制方法中，将数据请求和解析出来
        executeQuery(getService(ApiService::class.java,HttpConfig.NEWS_BASE_URL).queryNews(newsPage)
            ,callback)
    }
}