package com.workhub.global.feature.home.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.workhub.global.core.ui.BaseFragment
import com.workhub.global.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
}