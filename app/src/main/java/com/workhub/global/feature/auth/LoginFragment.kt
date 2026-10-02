package com.workhub.global.feature.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import com.workhub.global.core.ui.BaseFragment
import com.workhub.global.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )
    }
}