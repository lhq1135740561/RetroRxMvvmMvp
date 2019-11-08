package com.yunge.myretrofitrxlmvp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 需要去Manifest.xml中设置 (android:name=".MainApplication")才能被初始化
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}