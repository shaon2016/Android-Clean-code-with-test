package com.shaon2016.cleancodewithtest.view.activity.main

import android.os.Bundle
import com.shaon2016.cleancodewithtest.databinding.ActivityMainBinding
import com.shaon2016.cleancodewithtest.view.base.BaseActivity

class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun viewRelatedTask() {

    }
}
