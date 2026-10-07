package com.workhub.global.feature.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.workhub.global.core.ui.BaseFragment
import com.workhub.global.databinding.FragmentLoginBinding
import com.workhub.global.feature.auth.login.LoginViewModel
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

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

    override fun initView() {
        binding.viewModel = viewModel
        binding.state = viewModel.uiState.value
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.state = state
                }
            }
        }
    }
}
