package com.example.chatapppaddington.ui.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapppaddington.R
import com.example.chatapppaddington.databinding.FragmentHomeBinding
import com.example.chatapppaddington.ui.presentation.adapters.ChatAdapter
import com.example.chatapppaddington.data.model.User
import com.example.chatapppaddington.ui.presentation.MainActivity
import com.example.chatapppaddington.ui.presentation.base.BaseFragment
import com.example.chatapppaddington.ui.presentation.home.viewModel.HomeViewModelImp
import com.example.chatapppaddington.utils.NotificationUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var adapter: ChatAdapter
    override val viewModel: HomeViewModelImp by viewModels()
    override fun getLayoutResource() = R.layout.fragment_home

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        setupAdapter()

        binding?.run {
            btnAdd.setOnClickListener {
                (requireActivity() as MainActivity).startService()

//                val action = HomeFragmentDirections.actionHomeFragmentToMessageFragment()
////                val action= HomeFragmentDirections.
//                NavHostFragment.findNavController(this@HomeFragment).navigate(action)
            }

            btnNotification.setOnClickListener {
                NotificationUtils.createNotification(
                    requireContext(),
                    "First Notification",
                    "We are learning notification"
                )
            }

            btnNotificationRemoteInput.setOnClickListener {
                NotificationUtils.createNotificationWithPendingIntent(
                    requireContext(),
                    "Notification with pending intent",
                    "we are learning notification with pending intent"
                )
            }

            btnAdd2.setOnClickListener{
                (requireActivity() as MainActivity).stopService()
            }


        }


    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.users.observe(viewLifecycleOwner) {
            adapter.setChats(it.toMutableList())
        }
    }

    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ChatAdapter(mutableListOf())
        adapter.listener = object : ChatAdapter.Listener {
            override fun onClick(item: User) {
                val action = item.id.let {
                    HomeFragmentDirections.actionHomeFragmentToMessageFragment(
                        item.id
                    )
                }
                NavHostFragment.findNavController(this@HomeFragment).navigate(action)
            }
        }

        binding?.rvChat?.adapter = adapter
        binding?.rvChat?.layoutManager = layoutManager
    }
}