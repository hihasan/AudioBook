package com.hihasan.audioboo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hihasan.audioboo.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.TBL_PERMISSION)
data class PermissionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val status: Int
)