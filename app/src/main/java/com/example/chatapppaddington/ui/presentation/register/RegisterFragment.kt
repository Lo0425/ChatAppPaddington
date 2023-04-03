package com.example.chatapppaddington.ui.presentation.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapppaddington.R
import com.example.chatapppaddington.databinding.FragmentRegisterBinding
import com.example.chatapppaddington.ui.presentation.base.BaseFragment
import com.example.chatapppaddington.ui.presentation.register.viewModel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(){

    override val viewModel: RegisterViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_register

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        binding.run{
            binding?.toLogin?.setOnClickListener {
                val action= RegisterFragmentDirections.actionRegisterToLogin()
                NavHostFragment.findNavController(this@RegisterFragment).navigate(action)
            }
            binding?.btnRegister?.setOnClickListener {
                val username = binding?.etName?.text.toString()
                val password = binding?.etPassword?.text.toString()
                val email = binding?.etEmail?.text.toString()
                val id = UUID.randomUUID().toString()
                if (username.length < 2 && password.length < 6) {
                    val snackBar =
                        Snackbar.make(
                            binding!!.root,
                            "Please enter all the values",
                            Snackbar.LENGTH_LONG
                        )
                    snackBar.show()
                } else {
                    lifecycleScope.launch {
                        viewModel.register()
                    }
                }
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        lifecycleScope.launch {
            viewModel.finish.collect {
                val action = RegisterFragmentDirections.toLogin()
                navController.navigate(action)
            }
        }
    }


}