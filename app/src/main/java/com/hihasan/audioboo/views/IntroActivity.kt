package com.hihasan.audioboo.views

import android.app.Activity
import android.content.pm.PackageManager
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import com.hihasan.audioboo.R
import com.hihasan.audioboo.constants.ApplicationConstants
import com.hihasan.audioboo.utils.permission.PermissionDeniedAlertDialog
import com.hihasan.audioboo.utils.permission.RunTimePermissions
import java.lang.ref.WeakReference


class IntroActivity : AppIntro() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (RunTimePermissions(WeakReference<Activity>(activit)).isAllNecessaryPermissionGranted) {
//
//            phoneValidation(binding.mobileNoEdt.text.toString())
//        } else {
//            checkForRuntimePermissions()
//        }

        checkForRuntimePermissions()


        skipButtonEnabled = false

        addSlide(
            AppIntroFragment.newInstance(
                "C++", "C++ Self Paced Course",
                android.R.drawable.ic_menu_report_image, ContextCompat.getColor(applicationContext, R.color.black)
            )
        )

        // below line is for creating second slide.
        addSlide(
            AppIntroFragment.newInstance(
                "DSA", "Data Structures and Algorithms", android.R.drawable.ic_menu_report_image,
                ContextCompat.getColor(applicationContext, R.color.cancelColor)
            )
        )

        // below line is use to create third slide.
        addSlide(
            AppIntroFragment.newInstance(
                "Java", "Java Self Paced Course", android.R.drawable.ic_menu_report_image,
                ContextCompat.getColor(applicationContext, R.color.blue_grey)
            )
        )
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        Toast.makeText(applicationContext, "Test ", Toast.LENGTH_SHORT).show()
    }


    private fun checkForRuntimePermissions() {
        RunTimePermissions(WeakReference<Activity>(activity)).checkForPermissions()
    }

    private fun displayAlertToAllowAccessLocationPermissionFromAppSettings() {
        PermissionDeniedAlertDialog(WeakReference<Activity>(activity)).displayAlertForAccessLocationPermissionNotFound()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            ApplicationConstants.REQUEST_LOCATION_PERMISSIONS -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) return
                displayAlertToAllowAccessLocationPermissionFromAppSettings()
            }
        }
    }
}