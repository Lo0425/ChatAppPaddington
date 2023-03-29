package com.example.chatapppaddington.view.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapppaddington.R
import com.example.chatapppaddington.view.adapters.MessageAdapter
import com.example.chatapppaddington.databinding.FragmentMessageBinding
import com.example.chatapppaddington.viewModels.MessageViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MessageFragment : BaseFragment<FragmentMessageBinding>() {
    private lateinit var adapter: MessageAdapter
    override val viewModel: MessageViewModel by viewModels()
    private val args: MessageFragmentArgs by navArgs()

    override fun getLayoutResource() = R.layout.fragment_message

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        setupAdapter()
        binding?.viewModel = viewModel
    }

    override fun onBindData(view: View) {
        super.onBindData(view)


        lifecycleScope.launch {
            viewModel.getAllMessages(args.id).collect {
                adapter.setMessages(it.toMutableList())
            }
        }
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = MessageAdapter(mutableListOf(),requireContext())

        binding?.rvMessages?.adapter = adapter
        binding?.rvMessages?.layoutManager = layoutManager
    }
}
