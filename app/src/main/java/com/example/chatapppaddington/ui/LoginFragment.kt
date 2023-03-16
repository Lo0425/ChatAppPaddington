package com.example.chatapppaddington.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapppaddington.MainActivity
import com.example.chatapppaddington.R
import com.example.chatapppaddington.databinding.FragmentLoginBinding
import com.example.chatapppaddington.viewModels.LoginViewModel
import kotlinx.coroutines.launch

class LoginFragment :BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_login

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        binding?.toRegister?.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment2()
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding?.btnLogin?.setOnClickListener {
            val email =binding?.etEmail?.text.toString()
            val password = binding?.etPassword?.text.toString()
            lifecycleScope.launch {
//                viewModel.login(email,password)
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        lifecycleScope.launch {
            viewModel.loginFinish.collect{
                val action = LoginFragmentDirections.actionLoginFragmentToContactFragment2()
                navController.navigate(action)
            }
        }
    }

}