package com.workhub.global.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity <B: ViewDataBinding>: AppCompatActivity(){
    protected lateinit var binding: B
    protected abstract fun createBinding(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = createBinding()
        binding.lifecycleOwner = this

        setContentView(binding.root)

        initView()
        initListener()
        observeData()
    }
    protected open fun initView() = Unit

    protected open fun initListener() = Unit

    protected open fun observeData()= Unit
}