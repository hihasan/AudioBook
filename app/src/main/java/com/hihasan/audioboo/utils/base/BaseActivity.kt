package com.hihasan.audioboo.utils.base

import android.app.Activity
import android.app.Dialog
import android.app.DownloadManager
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.room.Room

import com.hihasan.audioboo.R
import com.hihasan.audioboo.constants.DatabaseConstants
import com.hihasan.audioboo.listeners.DialogActionListener
import com.hihasan.audioboo.utils.App
import com.hihasan.audioboo.utils.CustomLoadingDialog
import com.hihasan.audioboo.utils.DialogUtil
import com.hihasan.audioboo.utils.LocaleContextWrapper
import com.hihasan.audioboo.views.MainActivity
import java.io.File

open class BaseActivity : AppCompatActivity(){

    public open var dataBinding: ViewDataBinding? = null
    var database: BaseDatabase? = null


    override fun onStart() {
        super.onStart()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initialize dialog utils class for getting dialog util object
        dialogUtil = DialogUtil(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        database = let {
            Room.databaseBuilder(it, BaseDatabase::class.java, DatabaseConstants.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }

    override fun onResume() {
        super.onResume()
    }
    override fun onStop() {
        super.onStop()
    }


    //method for fragment calling
    protected fun initFragment(fragment: Fragment, id: String?, resId: Int) {
        supportFragmentManager
            .beginTransaction()
            .add(resId, fragment, id)
            .addToBackStack(null)
            .commit()
    }

    //method for fragment replace
    protected fun replaceFragment(fragment: Fragment, id: String?, resId: Int) {
        supportFragmentManager
            .beginTransaction()
            .add(resId, fragment, id)
            .addToBackStack(id)
            .commit()
    }

    //method for going to homepage
    fun gotoHomePage() {
        val intent: Intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        finish()
        startActivity(intent)
    }

    //base of all activity class
    var dialogUtil: DialogUtil? = null
    open fun showToast(ctx: Context?, msg: String?) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
    }

    open fun showToast(msg: String?) {
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, msg, duration)
        toast.show()
    }

    open fun errorToast(ctx: Context?) {
        Toast.makeText(ctx, "Something went wrong! Please try again later!", Toast.LENGTH_SHORT).show()
    }

    open fun showWait(msg: String?, context: Context?) {
        dialogUtil = context?.let { DialogUtil(it) }
        dialogUtil!!.showProgressDialog(msg)
    }



    open  fun hideWait() {
        if (dialogUtil == null) return
        dialogUtil!!.dismissProgress()
    }

    open  fun showError(err: Any) {
        hideWait()
        if (err is Exception) {
            showToast(App.getAppContext(), err.message)
        } else if (err is String) {
            showToast(App.getAppContext(), err.toString())
        } else {
            showToast(App.getAppContext(), "An error occurred")
        }
    }

    open fun showCustomDialog(title: String, activity: Activity, dialogActionListener: DialogActionListener) {


        val dialog = Dialog(this, R.style.WideDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.logout_dialog_layout)
        val dialogTitle = dialog.findViewById(R.id.logout_title_tv) as TextView
        dialogTitle.text = title
        val yesBtn = dialog.findViewById(R.id.dialog_yes_btn) as Button
        val noBtn = dialog.findViewById(R.id.dialog_no_btn) as Button
        yesBtn.setOnClickListener {
            dialogActionListener.dialogAction(true)
            dialog.dismiss()
        }
        noBtn.setOnClickListener {
            dialogActionListener.dialogAction(false)
            dialog.dismiss()
        }
        dialog.show()

    }
    open fun setLocal(activity: Activity, langCode: String){
        val context: Context =
            LocaleContextWrapper.wrap(activity, langCode)
        resources.updateConfiguration(
            context.resources.configuration,
            context.resources.displayMetrics
        )

    }

    protected open fun downloadImage(url: String?): String? {
        var path = ""
        if (url != null) {
            val filename = url.substring(url.lastIndexOf("/") + 1)
            val file =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).path + "MAJHI_FOLDER" + filename)
            path = file.path
            if (file.exists()) {
            } else {
                val request = DownloadManager.Request(Uri.parse(url))
                    .setTitle(filename)
                    .setDescription("Downloading")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setDestinationUri(Uri.fromFile(file))
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)
                val downloadManager =
                    applicationContext.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                val referenceID = downloadManager.enqueue(request)
            }
        }
        return path
    }


    open fun showCustomLoadingView(context: Context, title: String, param: Boolean) {
        if (param) {
            CustomLoadingDialog(context, title).show()
        } else {
            CustomLoadingDialog(context, title).dismiss()
        }
    }

    open fun getPathFromUri(context: Context, uri: Uri): String? {
        val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }

            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(
                    split[1]
                )
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {

            // Return the remote address
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(
                context,
                uri,
                null,
                null
            )
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }

    open fun getDataColumn(
        context: Context, uri: Uri?, selection: String?,
        selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(
            column
        )
        try {
            cursor = context.contentResolver.query(
                uri!!, projection, selection, selectionArgs,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val index: Int = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            if (cursor != null) cursor.close()
        }
        return null
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    open fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    open fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    open fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    open fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }

    fun View.showKeyboard() {
        this.requestFocus()
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}