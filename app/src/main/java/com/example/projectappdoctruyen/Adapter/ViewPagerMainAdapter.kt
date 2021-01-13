package com.example.projectappdoctruyen.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class ViewPagerMainAdapter : FragmentPagerAdapter {
    private var fragments: ArrayList<Fragment>? = null
    private var titles: ArrayList<String>? = null

    constructor(fm: FragmentManager) : super(fm) {
        fragments = ArrayList()
        titles = ArrayList()
    }

    constructor(fm: FragmentManager, behavior: Int) : super(fm, behavior) {}

    override fun getItem(position: Int): Fragment {
        return fragments!![position]
    }

    override fun getCount(): Int {
        return fragments!!.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragments!!.add(fragment)
        titles!!.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles!![position]
    }
}