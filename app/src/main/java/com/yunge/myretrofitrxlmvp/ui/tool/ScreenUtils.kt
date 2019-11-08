package com.yunge.myretrofitrxlmvp.ui.tool

import android.content.Context

class ScreenUtils {

    /**
     * 根据实际分辨率从 dp 的单位转变成 px(像素)
     */
    companion object {

        fun dip2px(context: Context, dp: Int): Int {
            val scale = context.resources.displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        fun px2dip(context: Context, pxValue: Int): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }
    }
}