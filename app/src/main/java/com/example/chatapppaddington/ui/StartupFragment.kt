package com.example.chatapppaddington.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapppaddington.R
import com.example.chatapppaddington.databinding.FragmentLoginBinding
import com.example.chatapppaddington.databinding.FragmentStartupBinding
import com.example.chatapppaddington.viewModels.LoginViewModel


class StartupFragment :BaseFragment<FragmentStartupBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_startup
    override fun onBindData(view: View) {
        super.onBindData(view)
//        binding.run {
//            binding?.btnToLogin?.setOnClickListener {
//                val action = StartupFragmentDirections.toLogin()
//                NavHostFragment.findNavController(this@StartupFragment).navigate(action)
//            }
//            binding?.tvToRegister?.setOnClickListener {
//                val action = StartupFragmentDirections.toLogin()
//                NavHostFragment.findNavController(this@StartupFragment).navigate(action)
//            }
//        }
    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
    }

}