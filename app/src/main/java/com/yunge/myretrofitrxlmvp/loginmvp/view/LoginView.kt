package com.yunge.myretrofitrxlmvp.loginmvp.view

import com.yunge.myretrofitrxlmvp.loginmvp.User

interface IUserLoginView {

    fun getUserName(): String

    fun getUserPassword(): String

    fun clearUserName()

    fun clearUserPassword()

    fun showLoading()

    fun hideLoading()

    fun toMainActivity(user: User)

    fun showFailedError(error: String)

    //跳转到天气页面
    fun loginWeather()
}