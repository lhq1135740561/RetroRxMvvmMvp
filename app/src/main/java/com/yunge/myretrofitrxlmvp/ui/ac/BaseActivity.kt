package com.yunge.myretrofitrxlmvp.ui.ac

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.yunge.myretrofitrxlmvp.weamvvm.http.IBaseViewModelObserver

abstract class BaseActivity : AppCompatActivity(), IBaseViewModelObserver{

//    lateinit var recyclerView:



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }


    override fun showLoading(msg: String) {

    }
    
    private fun getContext(): Context = this

    fun <T> startActivity(clz: Class<T>){

        getContext().startActivity(Intent(getContext(),clz))
    }

    fun <T> startActivity(clz: Class<T>,page: Int){

        getContext().startActivity(Intent(getContext(),clz).putExtra("page",page))
    }

    fun <T : ViewModel> getViewModel(clz: Class<T>) =
        ViewModelProviders.of(this)[clz]
}
