package edu.bluejack17_2.water18.fragment.customer.tab.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.adapter.tab.HomeTabAdapter
import edu.bluejack17_2.water18.fragment.HomeFragment

class HomeHistoryParentFragment : Fragment(), View.OnClickListener
{
    companion object
    {
        fun newInstance() = HomeHistoryParentFragment()
    }

    private fun initTabs()
    {
        var adapter = HomeTabAdapter(childFragmentManager,2)
        var viewPager = view?.findViewById<ViewPager>(R.id.pager)
        adapter.addFragment(HomeFragment.newInstance(),"Home")
        adapter.addFragment(HistoryFragment.newInstance(),"History")
        viewPager?.adapter =adapter

        var tabLayout = view?.findViewById<TabLayout>(R.id.tabs)
        tabLayout?.setupWithViewPager(viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let{

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        initTabs()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onClick(src: View?)
    {

    }
}