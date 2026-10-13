package com.workhub.global.feature.services

import android.view.LayoutInflater
import android.view.ViewGroup
import com.workhub.global.core.ui.BaseFragment
import com.workhub.global.databinding.FragmentServicesBinding

class ServicesFragment : BaseFragment<FragmentServicesBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentServicesBinding {
        return FragmentServicesBinding.inflate(inflater, container, false)
    }
}