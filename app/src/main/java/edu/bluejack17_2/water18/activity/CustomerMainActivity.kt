package edu.bluejack17_2.water18.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.firebase.controller.FirebaseUserController
import edu.bluejack17_2.water18.fragment.customer.CartFragment
import edu.bluejack17_2.water18.fragment.customer.OrderFragment
import edu.bluejack17_2.water18.fragment.customer.tab.view.HomeHistoryParentFragment
import edu.bluejack17_2.water18.model.Product
import kotlinx.android.synthetic.main.drawer_navigation.*
import org.jetbrains.anko.toast

class CustomerMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
                             OrderFragment.OnListFragmentInteractionListener
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_main)
        setSupportActionBar(toolbar)
        initDrawer()
        initFragment()
    }

    private fun initFragment()
    {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame,HomeHistoryParentFragment.newInstance() as Fragment)
            commit()
        }
    }

    private fun initDrawer()
    {
        navigation_view.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawer_navigation_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        if(drawer_navigation_layout==null)
        {
            toast("null")
            return
        }
        drawer_navigation_layout.addDrawerListener(toggle)
        toggle.syncState()
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
                R.id.nav_home -> HomeHistoryParentFragment.newInstance()
                R.id.nav_cart -> CartFragment.newInstance()
                R.id.nav_order -> OrderFragment.newInstance()
                R.id.nav_sign_out -> {
                    signOut()
                    return false
                }
                else -> null
            } ?: return false

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame,fragment as Fragment)
            commit()
        }
        //drawer_navigation_layout.closeDrawers()

        return true
    }

    override fun onListFragmentInteraction(item: Product?)
    {

    }

    fun signOut()
    {
        FirebaseUserController.signOut()
    }

}
