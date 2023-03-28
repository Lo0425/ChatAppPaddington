package com.example.chatapppaddington.view.fragments.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapppaddington.R
import com.example.chatapppaddington.databinding.FragmentLoginBinding
import com.example.chatapppaddington.view.fragments.BaseFragment
import com.example.chatapppaddington.viewModels.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_login

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        binding?.btnLogin?.setOnClickListener {
            val email =binding?.etEmail?.text.toString()
            val password = binding?.etPassword?.text.toString()
            lifecycleScope.launch {
                viewModel.login()
            }
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