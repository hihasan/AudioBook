package com.hihasan.audioboo.dao

import androidx.room.Dao
import androidx.room.Query
import com.hihasan.audioboo.constants.DatabaseConstants
import com.hihasan.audioboo.entity.PermissionEntity
import com.hihasan.audioboo.utils.base.BaseDao

@Dao
interface PermissionDao : BaseDao<PermissionEntity> {

    @Query("SELECT * FROM ${DatabaseConstants.TBL_PERMISSION}")
    fun getStatus () : List<PermissionEntity>
}