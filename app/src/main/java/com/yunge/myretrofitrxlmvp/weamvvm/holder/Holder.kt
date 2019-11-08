package com.yunge.myretrofitrxlmvp.weamvvm.holder

import android.content.Context
import android.widget.Toast
import com.yunge.myretrofitrxlmvp.MainApplication

class ContextHolder{

    companion object {

        val context: Context by lazy { MainApplication.context }
    }
}

class ToastHolder{
    companion object {
        fun setMessage(msg: String){
            Toast.makeText(ContextHolder.context,msg,Toast.LENGTH_SHORT).show()
        }
    }
}