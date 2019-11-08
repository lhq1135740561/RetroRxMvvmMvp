package com.yunge.myretrofitrxlmvp.ui

import android.os.Bundle
import com.yunge.myretrofitrxlmvp.R
import com.yunge.myretrofitrxlmvp.ui.ac.BaseActivity
import com.yunge.myretrofitrxlmvp.ui.ac.PhotoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_btn.setOnClickListener {

            startActivity(PhotoActivity::class.java)
        }
    }
}
