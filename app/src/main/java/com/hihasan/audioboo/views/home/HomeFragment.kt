package com.hihasan.audioboo.views.home

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.appnap.majhi.customer.utils.base.BaseFragment
import com.hihasan.audioboo.R
import com.hihasan.audioboo.databinding.FragmentHomeBinding
import com.hihasan.audioboo.factory.HomeViewModelFactory

class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private val homeUseCase = HomeUseCase()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, HomeViewModelFactory()).get(HomeViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeUseCase.initListeners(
            binding.tabView,
            binding.viewpager,
            lifecycle,
            childFragmentManager
        )
        homeUseCase.getPdfList(activity!!.applicationContext)
        homeUseCase.searchQuery(binding.icSearch)
        homeUseCase.searchListener(binding.icSearch, binding.appName, binding.icAdd)
        homeUseCase.searchCloseListeners(binding.icSearch, binding.appName, binding.icAdd)
        binding.icAdd.setOnClickListener {
            homeUseCase.onMoreButtonClick(binding.icAdd, requireContext())
        }

    }


}