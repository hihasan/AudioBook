package com.hihasan.audioboo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hihasan.audioboo.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.TBL_PDF_LIST)
data class PdfListEntity(
    @PrimaryKey(autoGenerate = true)
    val sl_no : Int,
    val name : String,
    val size : String,
    val uri : String,
    val last_added : String
)
