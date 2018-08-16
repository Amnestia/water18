package edu.bluejack17_2.water18.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.fragment.HomeFragment
import edu.bluejack17_2.water18.fragment.customer.CartFragment
import edu.bluejack17_2.water18.fragment.customer.OrderFragment
import kotlinx.android.synthetic.main.drawer_navigation.*

class AdminMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        setSupportActionBar(toolbar)
        initDrawer()
    }

    private fun initDrawer()
    {
        val toggle = ActionBarDrawerToggle(this, drawer_navigation_layout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_navigation_layout.addDrawerListener(toggle)
        toggle.syncState()

        navigation_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed()
    {
        if(drawer_navigation_layout.isDrawerOpen(GravityCompat.START))
        {
            drawer_navigation_layout.closeDrawer(GravityCompat.START)
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
        drawer_navigation_layout.closeDrawers()

        return true
    }
}
