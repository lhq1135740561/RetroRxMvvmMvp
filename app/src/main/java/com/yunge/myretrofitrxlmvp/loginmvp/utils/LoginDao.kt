package com.yunge.myretrofitrxlmvp.loginmvp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object LoginDao {

    //存储登录用户名
    fun cacheLoginUserNamePassword(username: String?,password: String?,context: Context){
        if (username == null || password == null) return
        context.getSharedPreferences("login",MODE_PRIVATE).edit {
            putString("login_username",username)
            putString("login_password",password)
        }
    }

    //获取存储登录用户名
    fun getCacheLoginUserName(context: Context): String? {

        return context.getSharedPreferences("login", MODE_PRIVATE).getString("login_username",null)
    }

    //获取存储登录用户的密码
    fun getCacheLoginUserPassword(context: Context): String? {

        return context.getSharedPreferences("login", MODE_PRIVATE).getString("login_password",null)
    }


    //扩展轻量级存储
    private fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit){
        val edit = edit()
        action(edit)
        edit.apply()
    }
}