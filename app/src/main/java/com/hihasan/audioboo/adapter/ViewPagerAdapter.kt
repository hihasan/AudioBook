package com.hihasan.audioboo.adapter

import android.util.Log
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.paging.Pager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hihasan.audioboo.views.home.audio.AudioFragment
import com.hihasan.audioboo.views.home.pdf.PdfFragment

class ViewPagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle, private val titleList: List<String>, private val type: Int) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return AudioFragment()
            1 -> return PdfFragment()
        }

        return AudioFragment()
    }

//    override fun createFragment(position: Int): Fragment {
//        Log.d("ViewPager Adapter", titleList.indexOf(titleList[position]).toString())
//        return Pager.newInstance(position,type,titleList[position])
//    }




}