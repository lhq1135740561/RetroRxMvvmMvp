package com.yunge.myretrofitrxlmvp.ui.ac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yunge.myretrofitrxlmvp.R
import com.yunge.myretrofitrxlmvp.newsmvp.model.NewsBeanData
import com.yunge.myretrofitrxlmvp.newsmvp.model.NewsModel
import com.yunge.myretrofitrxlmvp.newsmvp.presenter.NewsPresenter
import com.yunge.myretrofitrxlmvp.newsmvp.view.IView
import com.yunge.myretrofitrxlmvp.newsmvp.vmodel.NewsViewModel
import com.yunge.myretrofitrxlmvp.ui.adapter.NewsAdapter
import com.yunge.myretrofitrxlmvp.weamvvm.vmodel.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : BaseActivity(),NewsAdapter.OnClickNewLisetener{

    //跳转页面
    override fun onItemClick(page: Int) {

        startActivity(NewsItemActivity::class.java,page)
    }

    private lateinit var newsViewModel: NewsViewModel

    private val newsBeanDataLists = mutableListOf<NewsBeanData>()

    private val newsAdapter = NewsAdapter(newsBeanDataLists,this,this)


//    private lateinit var newsPresenter: NewsPresenter

//    private val newsModel = NewsModel()

    //View接口传过来的方法
//    override fun setNews(newsBeanDataList: List<NewsBeanData>) {
//
//        val manager = LinearLayoutManager(this)
//        news_rv.layoutManager = manager
//        newsAdapter = NewsAdapter(newsBeanDataList,this)
//        news_rv.adapter = newsAdapter
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val manager = LinearLayoutManager(this)
        news_rv.layoutManager = manager
        news_rv.adapter = newsAdapter

    }

    override fun initViewModel(): BaseViewModel? {
        newsViewModel = getViewModel(NewsViewModel::class.java)

        //通过观察者模式，来观察数据的变化
        newsViewModel.newsDataBeanLists.observe(this, Observer {
            showNewsData(it)
        })

        return newsViewModel
    }

    private fun showNewsData(list: List<NewsBeanData>?) {
        newsBeanDataLists.clear()
        if (list != null) {
            newsBeanDataLists.addAll(list)
            newsAdapter.notifyDataSetChanged()
        }


    }


    override fun onResume() {
        super.onResume()

        //设置请求网络数据并解析数据
        newsViewModel.queryNews(1)

    }
}
