package com.workhub.global.feature.auth.presentation.login

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.workhub.global.R
import com.workhub.global.core.ui.BaseFragment
import com.workhub.global.databinding.FragmentLoginBinding
import com.workhub.global.WorkHubApplication
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            (requireContext().applicationContext as WorkHubApplication)
                .authDependencies
                .loginUseCase
        )
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun initView() {
        binding.viewModel = viewModel
        binding.state = viewModel.uiState.value
    }

    override fun observeData() {
        observeLoading(viewModel)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { binding.state = it }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                    when (event) {
                        LoginEvent.LoginSuccess -> {
                            findNavController().navigate(
                                R.id.action_loginFragment_to_homeFragment
                            )
                        }

                        is LoginEvent.ShowMessage -> {
                            Toast.makeText(
                                requireContext(),
                                event.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}
