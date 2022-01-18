package com.hihasan.audioboo.utils.permission

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.ref.WeakReference

class RunTimePermissions(private val activity: WeakReference<Activity>) {
    //-----------------------------Location Permission Request to System--------------------------//
    @RequiresApi(Build.VERSION_CODES.R)
    fun checkForPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
        if (isAllNecessaryPermissionGranted) return
        requestForPermissions()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @TargetApi(Build.VERSION_CODES.M)
    private fun requestForPermissions() {
        activity.get()!!.requestPermissions(allPermissions.toTypedArray(), 0)
    }

    //______________________________________________________________________________________________
    //----------------------------------Checking Location Permission------------------------------//
    val isAllNecessaryPermissionGranted: Boolean
        @RequiresApi(Build.VERSION_CODES.R)
        get() {
            val permissions = allPermissions
            for (permission in permissions) {
                if (activity.get()!!
                        .checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED || activity.get()!!
                        .checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_DENIED
                ) return false
            }
            return true
        }

    private val allPermissions: List<String>
        @RequiresApi(Build.VERSION_CODES.R) get() {
            val permissions: MutableList<String> = ArrayList()
            permissions.add(Manifest.permission.INTERNET)
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            permissions.add(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
            return permissions
        }

}