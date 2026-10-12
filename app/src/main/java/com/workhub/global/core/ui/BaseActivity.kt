package com.workhub.global.core.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.workhub.global.R

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
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

    protected open fun observeData() = Unit

    fun setLoadingVisible(visible: Boolean) {
        binding.root.findViewById<View>(R.id.loadingOverlay)?.visibility =
            if (visible) View.VISIBLE else View.GONE
    }
}
