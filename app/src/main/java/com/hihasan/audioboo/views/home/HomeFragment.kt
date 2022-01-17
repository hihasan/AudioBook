package com.hihasan.audioboo.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.appnap.majhi.customer.utils.base.BaseFragment
import com.hihasan.audioboo.databinding.FragmentHomeBinding
import com.hihasan.audioboo.factory.HomeViewModelFactory

class HomeFragment : BaseFragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, HomeViewModelFactory()).get(HomeViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}