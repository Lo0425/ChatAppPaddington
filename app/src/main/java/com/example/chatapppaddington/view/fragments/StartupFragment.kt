package com.example.chatapppaddington.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.chatapppaddington.R
import com.example.chatapppaddington.databinding.FragmentStartupBinding
import com.example.chatapppaddington.viewModels.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartupFragment : BaseFragment<FragmentStartupBinding>() {
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