package edu.bluejack17_2.water18.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.firebase.controller.FirebaseUserController
import edu.bluejack17_2.water18.fragment.HomeFragment
import edu.bluejack17_2.water18.fragment.admin.CustomerTransactionFragment
import edu.bluejack17_2.water18.fragment.admin.StockFragment
import edu.bluejack17_2.water18.fragment.admin.TransactionAdminFragment
import edu.bluejack17_2.water18.fragment.customer.OrderFragment
import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.model.Transaction
import edu.bluejack17_2.water18.service.NotificationService
import edu.bluejack17_2.water18.service.TrackerService
import edu.bluejack17_2.water18.storage.TransactionStorage
import edu.bluejack17_2.water18.storage.UserStorage
import kotlinx.android.synthetic.main.drawer_navigation_admin.*
import kotlinx.android.synthetic.main.drawer_navigation_header.view.*
import org.jetbrains.anko.intentFor



class AdminMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
                          StockFragment.OnListFragmentInteractionListener, OrderFragment.OnListFragmentInteractionListener,
                          TransactionAdminFragment.OnListFragmentInteractionListener, CustomerTransactionFragment.OnListFragmentInteractionListener
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        setSupportActionBar(toolbar)
        initDrawer()
        initFragment()
        startService(intentFor<NotificationService>())
    }

    private fun initFragment()
    {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame,HomeFragment.newInstance() as Fragment)
            commit()
        }
    }

    private fun initDrawer()
    {
        val headerView=navigation_view.getHeaderView(0)
        headerView.txt_address.text = UserStorage.user?.address.toString()
        headerView.txt_phone.text = UserStorage.user?.phoneNumber.toString()
        navigation_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed()
    {
/*        if(drawer_navigation_layout.isDrawerOpen(GravityCompat.START))
        {
            drawer_navigation_layout.closeDrawer(GravityCompat.START)
        }
        else
        {*/
            super.onBackPressed()
        //}
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        val fragment:Fragment?=
        when(item.itemId)
        {
            R.id.nav_home -> HomeFragment.newInstance()
            R.id.nav_stock -> StockFragment.newInstance()
            R.id.nav_order -> CustomerTransactionFragment.newInstance()
            R.id.nav_sign_out -> signOut()
            else -> null
        } ?: return false
        moveFragment(fragment!!)
        //drawer_navigation_layout.closeDrawers()

        return true
    }

    fun moveFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame,fragment)
            commit()
        }
    }

    fun signOut() : Fragment?
    {
        stopService(Intent(this@AdminMainActivity, TrackerService::class.java))
        FirebaseUserController.signOut()
        startActivity(intentFor<LoginActivity>())
        return null
    }

    override fun onListFragmentInteraction(item: Product?)
    {

    }

    override fun onListFragmentInteraction(transaction: Transaction)
    {
        TransactionStorage.transaction=transaction
        moveFragment(TransactionAdminFragment.newInstance())
    }
}
