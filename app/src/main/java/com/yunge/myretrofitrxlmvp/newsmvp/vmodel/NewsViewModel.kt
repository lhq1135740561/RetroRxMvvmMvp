package com.yunge.myretrofitrxlmvp.newsmvp.vmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.yunge.myretrofitrxlmvp.MainApplication
import com.yunge.myretrofitrxlmvp.newsmvp.http.callback.RequestCallback
import com.yunge.myretrofitrxlmvp.newsmvp.http.model.NewsClass
import com.yunge.myretrofitrxlmvp.newsmvp.model.NewsBeanData
import com.yunge.myretrofitrxlmvp.newsmvp.model.NewsModel
import com.yunge.myretrofitrxlmvp.newsmvp.presenter.NewsPresenter
import com.yunge.myretrofitrxlmvp.newsmvp.view.IView
import com.yunge.myretrofitrxlmvp.weamvvm.vmodel.base.BaseViewModel

class NewsViewModel : BaseViewModel(){ //,IView{

//    private val newsModel = NewsModel()

    var newsDataBeanLists = MutableLiveData<List<NewsBeanData>>()

    //View层传过来的接口方法
//    override fun setNews(newsBeanDataList: List<NewsBeanData>) {
//        if (newsBeanDataList.isNotEmpty()){
//            newsDataBeanLists.value = newsBeanDataList
//            Toast.makeText(MainApplication.context,"网络请求成功",Toast.LENGTH_SHORT).show()
//        }
//    }

    private val newsPresenter = NewsPresenter()

    //请求网络数据和解析数据方法
    fun queryNews(newsPage: Int){

        newsPresenter.setJson(newsPage,object : RequestCallback<List<NewsBeanData>>{
            override fun onSuccess(data: NewsClass<List<NewsBeanData>>) {
                if (data.data.isNotEmpty()) newsDataBeanLists.value = data.data
                Toast.makeText(MainApplication.context,"解析数据成功",Toast.LENGTH_SHORT).show()
            }

        })
    }

}