package com.hihasan.audioboo.views

import android.os.Bundle
import androidx.core.content.ContextCompat

import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import com.hihasan.audioboo.R


class IntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
}