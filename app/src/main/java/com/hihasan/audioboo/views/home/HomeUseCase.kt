package com.hihasan.audioboo.views.home

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hihasan.audioboo.adapter.ViewPagerAdapter

class HomeUseCase {

    val homeTitle = arrayListOf<String>("Audio Book", "Book List")

    fun initListeners(tabView: TabLayout, viewpager: ViewPager2, lifecycle: Lifecycle, childFragmentManager : FragmentManager) {
        viewpager.adapter =
            ViewPagerAdapter(childFragmentManager, lifecycle, homeTitle, 0)
        TabLayoutMediator(tabView, viewpager) { tab: TabLayout.Tab, position: Int ->
            tab.text = homeTitle[position]
        }.attach()

        tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}