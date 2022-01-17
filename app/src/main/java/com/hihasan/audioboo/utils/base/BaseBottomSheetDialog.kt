package com.hihasan.audioboo.utils.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hihasan.audioboo.constants.DatabaseConstants
import com.hihasan.audioboo.utils.base.BaseDatabase

open class BaseBottomSheetDialog : BottomSheetDialogFragment(){
    var database: BaseDatabase? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = activity?.let {
            Room.databaseBuilder(it, BaseDatabase::class.java, DatabaseConstants.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }

    }

    companion object {
        @JvmStatic
        fun showToast(ctx: Context?, msg: String?) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
        }
    }
}