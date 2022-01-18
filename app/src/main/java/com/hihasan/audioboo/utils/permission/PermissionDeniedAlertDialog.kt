package com.hihasan.audioboo.utils.permission

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hihasan.audioboo.R
import java.lang.ref.WeakReference

class PermissionDeniedAlertDialog(private val activity: WeakReference<Activity>) {
    @SuppressLint("StringFormatInvalid")
    fun displayAlertForAccessLocationPermissionNotFound() {
        if (activity.get() == null) return
        MaterialAlertDialogBuilder(activity.get()!!)
            .setCancelable(false)
            .setTitle(R.string.android_permission_alert_dialog_title)
            .setMessage(
                String.format(
                    activity.get()!!.getString(R.string.android_permission_denied_msg),
                    activity.get()!!.getString(activity.get()!!.applicationInfo.labelRes)
                )
            )
            .setPositiveButton(R.string.android_permission_goto_app_settings_text,
                DialogInterface.OnClickListener { dialog, which -> startInstalledAppDetailsActivity() })
            .show()
    }

    private fun startInstalledAppDetailsActivity() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", activity.get()!!.packageName, null)
        intent.data = uri
        activity.get()!!.startActivity(intent)
    }
}