package com.yunge.myretrofitrxlmvp.loginmvp.model

import android.os.Handler
import com.yunge.myretrofitrxlmvp.loginmvp.User


/**
 * 定义业务接口
 */
interface IUserBiz {

    fun userLogin(username: String, password: String, loginListener: OnLoginListener<User>)
}

/**
 * 定义回调接口
 */
interface OnLoginListener<T> {

    fun loginSuccess(data: T)

    fun loginFailed(error: String)
}


class UserBiz : IUserBiz {


    override fun userLogin(
        username: String,
        password: String,
        loginListener: OnLoginListener<User>
    ) {

        //模拟子线程耗时操作
        Thread(Runnable {
            Thread.sleep(2000)


            //模拟登录成功
            if (username == "lhq@qq.com" && password == "123456") {
                val user = User(username, password)
                loginListener.loginSuccess(user)
            } else if (username != "lhq@qq.com" && password == "123456") {
                loginListener.loginFailed("账号错误！")
            } else if (username == "lhq@qq.com" && password != "123456") {
                loginListener.loginFailed("密码错误！")
            }


        }).start()
    }


}