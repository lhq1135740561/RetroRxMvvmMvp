package com.yunge.myretrofitrxlmvp.weamvvm.http

import com.yunge.myretrofitrxlmvp.weamvvm.vmodel.base.BaseViewModel

interface IBaseViewModelEvent{

    fun showLoading(msg: String)
}



interface IBaseViewModelObserver : IBaseViewModelEvent{

    fun initViewModel(): BaseViewModel? {

        return null
    }


}