package com.example.chatapppaddington.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.chatapppaddington.R
import com.example.chatapppaddington.databinding.FragmentProfileBinding
import com.example.chatapppaddington.databinding.FragmentRegisterBinding
import com.example.chatapppaddington.viewModels.LoginViewModel
import com.example.chatapppaddington.viewModels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment :BaseFragment<FragmentProfileBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_profile
    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        binding.run{
            binding?.btnLogout?.setOnClickListener {
                viewModel.logout()
            }
        }
    }
    override fun onBindData(view: View) {
        super.onBindData(view)
        lifecycleScope.launch {
            viewModel.logoutFinish.collect{
                val action = ProfileFragmentDirections.toLogin()
                navController.navigate(action)
            }
        }
    }



}