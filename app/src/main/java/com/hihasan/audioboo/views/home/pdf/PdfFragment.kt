package com.hihasan.audioboo.views.home.pdf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.appnap.majhi.customer.utils.base.BaseFragment
import com.hihasan.audioboo.databinding.FragmentPdfBinding
import com.hihasan.audioboo.databinding.FragmentPdfListSingleBinding
import com.hihasan.audioboo.entity.PdfListEntity
import com.hihasan.audioboo.utils.BaseAdapter

class PdfFragment : BaseFragment(), BaseAdapter.BaseAdapterListener {
    private lateinit var binding : FragmentPdfBinding
    private lateinit var viewModel: PdfViewModel

    private val pdfUseCase = PdfUseCase()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPdfBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PdfViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        pdfList = database
        pdfUseCase.setRecyclerView(requireContext(), database!!.pdfListDao.getPdfList(), binding.bookRv, this)

    }

    override fun onBind(holder: BaseAdapter.DataBindingViewHolder, position: Int, item: Any?, layoutId: Int, ) {
        (holder.binding as FragmentPdfListSingleBinding).apply {
            pdf = item as PdfListEntity

//            readAbleFormat(holder.binding.sizeTv.toString())
            holder.binding.menuIv.setOnClickListener {
                Toast.makeText(requireContext(), "Action Needed", Toast.LENGTH_SHORT)
            }
        }
    }
}