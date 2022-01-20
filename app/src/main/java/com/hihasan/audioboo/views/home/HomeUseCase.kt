package com.hihasan.audioboo.views.home


import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hihasan.audioboo.R
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

    fun onMoreButtonClick(view: View, context: Context) {
        val popupMenu = PopupMenu(context, view)

        popupMenu.inflate(R.menu.menu_choose_converter)
        popupMenu.gravity = Gravity.END
        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass
                .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(mPopup, true)
        } catch (e: Exception){
            Log.e("Main", "Error showing menu icons.", e)
        } finally {
            popupMenu.show()
        }


        popupMenu.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.pdf_to_audio ->{
                    Toast.makeText(context, "PDF TO AUDIO", Toast.LENGTH_SHORT).show()
                }
                R.id.image_to_audio ->{
                    Toast.makeText(context, "IMAGE TO AUDIO", Toast.LENGTH_SHORT).show()
                }

            }
            true
        }

        // popupMenu?.show()
    }

    fun searchQuery(search : SearchView){
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    fun searchListener(search : SearchView, toolbarTitle: AppCompatTextView, icAdd : AppCompatImageView){
        search.setOnCloseListener {
            toolbarTitle.visibility = View.VISIBLE
            icAdd.visibility = View.VISIBLE

            val params: RelativeLayout.LayoutParams =
                search.layoutParams as RelativeLayout.LayoutParams
            params.width = RelativeLayout.LayoutParams.WRAP_CONTENT
            search.layoutParams = params
            search.requestLayout()

            false
        }
    }

    fun searchCloseListeners(search : SearchView, toolbarTitle: AppCompatTextView, icAdd : AppCompatImageView){
        search.setOnSearchClickListener {
            toolbarTitle.visibility = View.GONE
            icAdd.visibility = View.GONE

            val params: RelativeLayout.LayoutParams =
                search.layoutParams as RelativeLayout.LayoutParams
            params.width = RelativeLayout.LayoutParams.MATCH_PARENT
            params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
            search.layoutParams = params
            search.requestLayout()

        }
    }

    fun getPdfList(context: Context): ArrayList<String>? {
        val pdfList: ArrayList<String> = ArrayList()
        val projection = arrayOf(
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.MIME_TYPE
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
                    val columnData: Int = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)
                    val columnName: Int =
                        cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)
                    do {
                        pdfList.add(cursor.getString(columnData))
                        Log.d("Use Case", "getPdf: " + cursor.getString(columnName))
                        //you can get your pdf files
                    } while (cursor.moveToNext())
                }
            }
        return pdfList
    }
}