package com.hihasan.audioboo.views

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import com.hihasan.audioboo.R


class IntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

//    override fun onDonePressed() {
//        super.onDonePressed()
//
//    }
}