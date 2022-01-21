package com.hihasan.audioboo.views.home

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import com.hihasan.audioboo.entity.PdfListEntity
import com.hihasan.audioboo.utils.App
import com.hihasan.audioboo.utils.base.BaseViewModel

class HomeViewModel : BaseViewModel(context = App.getAppContext()) {

    private val listOfPdf: ArrayList<PdfListEntity> = arrayListOf()

    suspend fun getPdfList(context: Context): ArrayList<String>? {
        val pdfList: ArrayList<String> = ArrayList()
        val projection = arrayOf(
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.TITLE
        )
        val sortOrder = MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
        val selection = MediaStore.Files.FileColumns.MIME_TYPE + " = ?"
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf")
        val selectionArgs = arrayOf(mimeType)
        val collection: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Files.getContentUri("external")
        }
        context.contentResolver.query(collection, projection, selection, selectionArgs, sortOrder)
            .use { cursor ->
                assert(cursor != null)
                if (cursor!!.moveToFirst()) {

                    val pdfTitle: Int =
                        cursor.getColumnIndex(MediaStore.Files.FileColumns.TITLE)
                    val pdfSize: Int =
                        cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE)
                    val pdfUri: Int =
                        cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)
                    val pdfAdded : Int =
                        cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_ADDED)

                    do {
                        pdfList.add(cursor.getString(pdfUri))
                        listOfPdf.add(PdfListEntity(
                            0,
                            cursor.getString(pdfTitle),
                            cursor.getString(pdfSize),
                            cursor.getString(pdfUri),
                            cursor.getString(pdfAdded)
                        ))
                        Log.d("Use Case", "getPdf: " + cursor.getString(pdfAdded))
                        //you can get your pdf files
                    } while (cursor.moveToNext())
                }
            }

        database!!.pdfListDao.insertList(listOfPdf)
        return pdfList
    }
}