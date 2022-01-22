package com.hihasan.audioboo.views.home.pdf

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hihasan.audioboo.R
import com.hihasan.audioboo.entity.PdfListEntity
import com.hihasan.audioboo.model.PdfListModel
import com.hihasan.audioboo.utils.BaseAdapter

class PdfUseCase {
    private var tag : String = PdfUseCase::class.java.simpleName
    private var pdfAdapter : BaseAdapter<PdfListEntity>? = null

    fun setRecyclerView(
        context: Context,
        pdfListModel: List<PdfListEntity>,
        pdfListRv: RecyclerView,
        listener: BaseAdapter.BaseAdapterListener
    ){
        try {
            val layoutManager = LinearLayoutManager(context)
            pdfListRv.layoutManager = layoutManager
            pdfAdapter =
                BaseAdapter(context, pdfListModel, R.layout.fragment_pdf_list_single, listener)
            pdfListRv.adapter = pdfAdapter

        } catch (e: Exception) {
            Log.d(tag, "setRecyclerView: " + e.message)

        }
    }
}