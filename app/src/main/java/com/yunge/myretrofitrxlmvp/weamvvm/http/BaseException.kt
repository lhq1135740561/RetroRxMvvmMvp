package com.yunge.myretrofitrxlmvp.weamvvm.http

import com.yunge.myretrofitrxlmvp.weamvvm.http.config.HttpConfig

/**
 * 作者：leavesC
 * 时间：2019/5/31 10:48
 * 描述：
 */
sealed class BaseException(errorMessage: String, val code: Int = HttpConfig.CODE_UNKNOWN) :
    RuntimeException(errorMessage)

class ServerResultException(message: String, code: Int = HttpConfig.CODE_UNKNOWN) : BaseException(message, code)