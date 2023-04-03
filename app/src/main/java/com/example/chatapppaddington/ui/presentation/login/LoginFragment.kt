package com.example.chatapppaddington.ui.presentation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapppaddington.R
import com.example.chatapppaddington.databinding.FragmentLoginBinding
import com.example.chatapppaddington.ui.presentation.base.BaseFragment
import com.example.chatapppaddington.ui.presentation.login.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_login

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.btnLogin?.setOnClickListener {
            viewModel.login()
        }

        binding?.toRegister?.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment2()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        lifecycleScope.launch {
            viewModel.loginFinish.collect{
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                navController.navigate(action)

            }
        }

    }

    private fun navigateToHome(){
        (requireContext().applicationContext)
    }

}