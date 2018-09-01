package edu.bluejack17_2.water18.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.HistoryController
import edu.bluejack17_2.water18.controller.TransactionController
import edu.bluejack17_2.water18.firebase.controller.FirebaseUserController
import edu.bluejack17_2.water18.firebase.listener.HistoryGetDataListener
import edu.bluejack17_2.water18.fragment.customer.CartFragment
import edu.bluejack17_2.water18.fragment.customer.OrderFragment
import edu.bluejack17_2.water18.fragment.customer.TransactionFragment
import edu.bluejack17_2.water18.fragment.customer.tab.view.HistoryFragment
import edu.bluejack17_2.water18.fragment.customer.tab.view.HomeHistoryParentFragment
import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.model.Transaction
import edu.bluejack17_2.water18.storage.UserStorage
import kotlinx.android.synthetic.main.drawer_navigation.*
import kotlinx.android.synthetic.main.drawer_navigation_header.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class CustomerMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
                             OrderFragment.OnListFragmentInteractionListener, HistoryFragment.OnListFragmentInteractionListener,
                             CartFragment.OnListFragmentInteractionListener, GoogleApiClient
                             .OnConnectionFailedListener
{

    lateinit var ctx:Context

    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onConnectionFailed(connectionResult: ConnectionResult)
    {
        Log.d("Umm", "onConnectionFailed: " + connectionResult)
    }

    override fun onStart() 
    {
        super.onStart()
        try {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()

            mGoogleApiClient = GoogleApiClient.Builder(this).enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build()
        } catch (e: IllegalStateException){

        }

    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_main)
        setSupportActionBar(toolbar)
        HistoryController.readHistory(HistoryGetDataListener)
        initDrawer()
        initFragment()
        ctx=applicationContext
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
        val headerView=navigation_view.getHeaderView(0)
        headerView.txt_address.text = UserStorage.user?.address.toString()
        headerView.txt_phone.text = UserStorage.user?.phoneNumber.toString()
        navigation_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed()
    {
        /*if(drawer_navigation_layout.isDrawerOpen(GravityCompat.START))
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
            R.id.nav_home -> HomeHistoryParentFragment.newInstance()
            R.id.nav_cart -> CartFragment.newInstance()
            R.id.nav_order -> OrderFragment.newInstance()
            R.id.nav_current_order ->
            {
                if(!TransactionController.checkCurrentTransaction())
                {
                    toast("You do not have any ongoing transaction")
                    null
                }
                else
                TransactionFragment.newInstance()
            }
            R.id.nav_sign_out -> signOut()
            else -> null
        } ?: return false


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame,fragment as Fragment)
            commit()
        }
        //drawer_navigation_layout.closeDrawers()

        return true
    }

    fun signOut() : Fragment?
    {
        FirebaseUserController.signOut()

        //check if logged in using google account if available, log out from GOOGLE
        val googleSigned = GoogleSignIn.getLastSignedInAccount(this)

        //check if logged in using facebook account if available, log out from FACEBOOK
        val accessToken = AccessToken.getCurrentAccessToken()
        val facebookSigned = accessToken != null && !accessToken.isExpired

        //log out from GOOGLE
        if (googleSigned != null) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient)
        }
        //log out from FACEBOOK
        else if(facebookSigned){
            LoginManager.getInstance().logOut()
        }

        startActivity(intentFor<LoginActivity>())
        return null
    }

    override fun onListFragmentInteraction(item: Product?)
    {
        
    }

    override fun onListFragmentInteraction(history: Transaction?)
    {

    }

}
