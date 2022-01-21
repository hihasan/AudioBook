package com.hihasan.audioboo.views.home.pdf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.appnap.majhi.customer.utils.base.BaseFragment
import com.hihasan.audioboo.databinding.FragmentPdfBinding
import com.hihasan.audioboo.utils.BaseAdapter

class PdfFragment : BaseFragment(), BaseAdapter.BaseAdapterListener {
    private lateinit var binding : FragmentPdfBinding
    private lateinit var viewModel: PdfViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPdfBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PdfViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onBind(holder: BaseAdapter.DataBindingViewHolder, position: Int, item: Any?, layoutId: Int, ) {
    }
}