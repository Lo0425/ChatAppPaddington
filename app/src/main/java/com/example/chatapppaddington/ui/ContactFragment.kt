package com.example.chatapppaddington.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapppaddington.R
import com.example.chatapppaddington.databinding.FragmentContactBinding
import com.example.chatapppaddington.databinding.FragmentProfileBinding
import com.example.chatapppaddington.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment :BaseFragment<FragmentContactBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_contact
    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        binding.run {
            binding?.toProfile?.setOnClickListener {
                val action= ContactFragmentDirections.actionContactFragmentToProfileFragment2()
                NavHostFragment.findNavController(this@ContactFragment).navigate(action)
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
    }

}