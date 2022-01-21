package com.hihasan.audioboo.utils.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hihasan.audioboo.constants.DatabaseConstants
import com.hihasan.audioboo.dao.PdfListDao
import com.hihasan.audioboo.dao.PermissionDao
import com.hihasan.audioboo.entity.PdfListEntity
import com.hihasan.audioboo.entity.PermissionEntity


@Database(
    entities = [
        PermissionEntity::class,
        PdfListEntity::class
    ],
    version = DatabaseConstants.DATABASE_VERSION,
)
abstract class BaseDatabase : RoomDatabase() {

    abstract val permissionDao: PermissionDao
    abstract val pdfListDao: PdfListDao

    companion object {
        private var INSTANCE: BaseDatabase? = null

        fun getDatabase(contxt: Context): BaseDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        contxt, BaseDatabase::class.java, DatabaseConstants.DATABASE_NAME
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }


}