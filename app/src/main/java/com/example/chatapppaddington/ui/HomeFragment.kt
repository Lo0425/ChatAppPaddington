package com.example.chatapppaddington.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapppaddington.R
import com.example.chatapppaddington.adapter.ChatAdapter
import com.example.chatapppaddington.data.model.Chat
import com.example.chatapppaddington.databinding.FragmentHomeBinding
import com.example.chatapppaddington.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var adapter: ChatAdapter
    override val viewModel: HomeViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_home

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        setupAdapter()

        binding?.run {
            btnAdd.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToMessageFragment()
//                val action= HomeFragmentDirections.
                NavHostFragment.findNavController(this@HomeFragment).navigate(action)
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.chats.observe(viewLifecycleOwner) {
            adapter.setChats(it.toMutableList())
        }
    }

    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ChatAdapter(mutableListOf())
        adapter.listener = object : ChatAdapter.Listener {
            override fun onClick(item: Chat) {
                val action = item.id.let { HomeFragmentDirections.actionHomeFragmentToMessageFragment() }
//                val action = item.id.let{HomeFragmentDirections.actionHomeFragmentToChatFragment()}
                NavHostFragment.findNavController(this@HomeFragment).navigate(action)

            }
        }

        binding?.rvChat?.adapter = adapter
        binding?.rvChat?.layoutManager = layoutManager
    }
}