package com.hihasan.audioboo.dao

import androidx.room.Dao
import androidx.room.Query
import com.hihasan.audioboo.constants.DatabaseConstants
import com.hihasan.audioboo.entity.PdfListEntity
import com.hihasan.audioboo.utils.base.BaseDao

@Dao
interface PdfListDao : BaseDao<PdfListEntity> {

    @Query("SELECT * FROM ${DatabaseConstants.TBL_PDF_LIST}")
    fun getPdfList() : List<PdfListEntity>
}