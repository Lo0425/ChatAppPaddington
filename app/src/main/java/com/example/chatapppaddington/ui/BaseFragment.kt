package com.example.chatapppaddington.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.example.chatapppaddington.viewModels.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    abstract val viewModel: BaseViewModel
    lateinit var navController: NavController
    var binding: T? = null

    abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenResumed {
            viewModel.onViewCreated()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        onBindView(view, savedInstanceState)
        onBindData(view)
    }

    open fun onBindView(view: View, savedInstanceState: Bundle?) {
        binding = DataBindingUtil.bind(view)
        lifecycleScope.launch {
            viewModel.error.collect {
                val snackbar = Snackbar.make(view, it, Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
    }

    open fun onBindData(view: View) {

    }
}