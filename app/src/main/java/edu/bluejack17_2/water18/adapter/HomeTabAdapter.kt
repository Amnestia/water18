package edu.bluejack17_2.water18.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import edu.bluejack17_2.water18.fragment.tab.view.HistoryFragment
import edu.bluejack17_2.water18.fragment.tab.view.HomeFragment

class HomeTabAdapter(private val fm: FragmentManager, private val tabsCount: Int) : FragmentStatePagerAdapter(fm)
{
    override fun getCount(): Int
    {
        return tabsCount
    }

    override fun getItem(position: Int): Fragment?
    {
        return when(position)
        {
            0-> HomeFragment.newInstance()
            1-> HistoryFragment.newInstance()
            else -> null
        }
    }
}