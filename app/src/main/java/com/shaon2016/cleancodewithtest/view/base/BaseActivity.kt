package com.shaon2016.cleancodewithtest.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shaon2016.cleancodewithtest.util.ProgressBarHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    lateinit var progressBarHandler: ProgressBarHandler

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBarHandler = ProgressBarHandler(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewRelatedTask()
    }

    abstract fun viewRelatedTask()

}