package com.example.projectappdoctruyen.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.projectappdoctruyen.Fragment.ReadFragment
import com.example.projectappdoctruyen.Model.Chapter

class ViewPagerReadStoryAdapter(fm: FragmentManager, behavior: Int, var chapterArrayList: List<Chapter?>?) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        if (chapterArrayList == null || chapterArrayList!!.isEmpty()) {

        }
        val chapter = chapterArrayList!![position]
        val readFragment = ReadFragment()
        val bundle = Bundle()
        bundle.putSerializable("KEY", chapter)
        readFragment.arguments = bundle
        return readFragment
    }

    override fun getCount(): Int {
        return if (chapterArrayList != null) {
            chapterArrayList!!.size
        } else 0
        //        return chapterArrayList.size();
    }
}