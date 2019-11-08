package com.yunge.myretrofitrxlmvp.loginmvp.presenter

import android.os.Handler
import com.yunge.myretrofitrxlmvp.loginmvp.User
import com.yunge.myretrofitrxlmvp.loginmvp.model.OnLoginListener
import com.yunge.myretrofitrxlmvp.loginmvp.model.UserBiz
import com.yunge.myretrofitrxlmvp.loginmvp.view.IUserLoginView

//Presenter必须要能拿到View和Model的实现类
class UserLoginPresenter(private val userBiz: UserBiz, private val userLoginView: IUserLoginView) {

    private val mHandler = Handler()


    //登录
    fun login() {
        userLoginView.showLoading()

        userBiz.userLogin(userLoginView.getUserName(), userLoginView.getUserPassword(), object :
            OnLoginListener<User> {
            override fun loginSuccess(data: User) {
                //需要在ui线程执行
                mHandler.post {
                    userLoginView.toMainActivity(data)
                    userLoginView.hideLoading()
                }
            }

            override fun loginFailed(error: String) {
                mHandler.post {
                    //需要在ui线程中执行(因为使用了Toast)
                    userLoginView.showFailedError(error)
                    userLoginView.hideLoading()
                }
            }
        })
    }

    //清除
    fun clear() {
        userLoginView.clearUserName()
        userLoginView.clearUserPassword()
    }

    fun loginWeather(){
        userLoginView.loginWeather()
    }

}