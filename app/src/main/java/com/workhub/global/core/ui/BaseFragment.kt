package com.workhub.global.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    private var _binding: B? = null

    protected val binding: B
        get() = requireNotNull(_binding) {
            "Không được sử dụng binding trước onCreateView() hoặc sau onDestroyView()"
        }

    protected abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createBinding(
            inflater = inflater,
            container = container
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        initView()
        initListener()
        observeData()
    }

    protected open fun initView() = Unit

    protected open fun initListener() = Unit

    protected open fun observeData() = Unit

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}