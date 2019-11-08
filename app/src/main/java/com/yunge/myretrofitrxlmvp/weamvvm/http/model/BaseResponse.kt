package com.yunge.myretrofitrxlmvp.weamvvm.http.model

import com.google.gson.annotations.SerializedName

/**
 * 作者：leavesC
 * 时间：2019/5/31 10:58
 * 描述：
 */
class BaseResponse<T>(
    @SerializedName("status")
    var code: Int = 0,
    @SerializedName("info")
    var message: String? = null,
    @SerializedName("districts", alternate = ["forecasts"])
    var data: T
)

//OptionT用于代替BaseResponse对象
/**
 * 包装类 OptionT
 * 在解析数据时，将我们实际需要的数据 data 包装在 OptionT 当中，此时就无需关心 data 是否为 null
 */
class OptionT<T>(val value: T)