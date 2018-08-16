package edu.bluejack17_2.water18.adapter.tab

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import java.util.*

class HomeTabAdapter(private val fm: FragmentManager, private val tabsCount: Int) : FragmentStatePagerAdapter(fm)
{
    var fragments : MutableList<Fragment>  = ArrayList()
    var fragmentsTitle: MutableList<String> = ArrayList()

    override fun getCount(): Int
    {
        return tabsCount
    }

    override fun getItem(position: Int): Fragment?
    {
        return fragments.get(position)
    }

    fun addFragment(fragment: Fragment, title: String)
    {
        fragments.add(fragment)
        fragmentsTitle.add(title)
    }
}