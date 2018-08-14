package edu.bluejack17_2.water18.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.adapter.tab.HomeTabAdapter
import edu.bluejack17_2.water18.fragment.HomeFragment
import edu.bluejack17_2.water18.fragment.customer.CartFragment
import edu.bluejack17_2.water18.fragment.customer.OrderFragment
import edu.bluejack17_2.water18.fragment.customer.tab.view.HistoryFragment
import kotlinx.android.synthetic.main.drawer_navigation.*

class TabbedMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initDrawer()
        initTabs()
    }

    private fun initTabs()
    {
        var viewPager = findViewById<ViewPager>(R.id.pager)
        var adapter = HomeTabAdapter(supportFragmentManager,2)
        adapter.addFragment(HomeFragment.newInstance(),"Home")
        adapter.addFragment(HistoryFragment.newInstance(),"History")
        viewPager.adapter=adapter

        var tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun initDrawer()
    {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navigation_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed()
    {
        if(drawer_layout.isDrawerOpen(GravityCompat.START))
        {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else
        {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        val fragment:Fragment?=
            when(item.itemId)
            {
                R.id.nav_home -> HomeFragment.newInstance()
                R.id.nav_cart -> CartFragment.newInstance()
                R.id.nav_order -> OrderFragment.newInstance()
                else -> null
            } ?: return false

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame,fragment as Fragment)
            commit()
        }
        drawer_layout.closeDrawers()

        return true
    }
}
