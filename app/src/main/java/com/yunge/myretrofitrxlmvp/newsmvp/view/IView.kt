package com.yunge.myretrofitrxlmvp.newsmvp.view

import com.yunge.myretrofitrxlmvp.newsmvp.model.NewsBeanData
import com.yunge.myretrofitrxlmvp.newsmvp.model.NewsModel

/**
 * 只与用户交互(View层是以接口的形式定义，我们不关心数据，不关心逻辑处理！)
 */
interface IView {

    fun setNews(newsBeanDataList: List<NewsBeanData>)
}