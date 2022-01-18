package com.hihasan.audioboo.adapter

import android.util.Log
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.paging.Pager
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle, private val titleList: List<String>, private val type: Int) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return TestFragment()
            1 -> return TestFragment1()
        }

        return TestFragment()
    }

//    override fun createFragment(position: Int): Fragment {
//        Log.d("ViewPager Adapter", titleList.indexOf(titleList[position]).toString())
//        return Pager.newInstance(position,type,titleList[position])
//    }




}