package edu.bluejack17_2.water18.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.ProductController
import edu.bluejack17_2.water18.controller.TransactionController
import edu.bluejack17_2.water18.controller.UserController
import edu.bluejack17_2.water18.firebase.listener.NotificationGetDataListener
import edu.bluejack17_2.water18.firebase.listener.ProductGetDataListener
import edu.bluejack17_2.water18.firebase.listener.TransactionGetDataListener
import edu.bluejack17_2.water18.firebase.listener.UserGetDataListener
import edu.bluejack17_2.water18.notification.controller.NotificationController
import edu.bluejack17_2.water18.storage.UserStorage
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), View.OnClickListener, GoogleApiClient.OnConnectionFailedListener
{

    override fun onConnectionFailed(connectionResult: ConnectionResult)
    {
        Log.d("Umm", "onConnectionFailed: " + connectionResult)
    }

    private fun addListener()
    {
        arrayOf(btn_login ,btn_login_facebook ,btn_login_google ,btn_sign_up).forEach{ it.setOnClickListener(this) }
    }

    private fun init()
    {
        ProductController.read(ProductGetDataListener)
        TransactionController.readTransaction(TransactionGetDataListener)
        UserController.read(UserGetDataListener)
        NotificationController.read(NotificationGetDataListener)
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addListener()
        init()
    }

    override fun onClick(src: View?)
    {
        when(src){
            btn_sign_up->signUp()
            btn_login->login()
            btn_login_facebook->loginWithFacebook()
            btn_login_google ->loginWithGoogle()
            else -> return
        }
    }

    fun signUp()
    {
        startActivity(intentFor<SignUpWithPhoneNumberActivity>())
    }

    fun login()
    {
        val phone=tf_phone_login.text.toString()
        val password=pf_password.text.toString()
        val user= UserController.login(phone,password)
        if(user!=null)
            UserStorage.user=user
        else
        {
            toast("Incorrect phone or password")
            return
        }
        if(UserStorage.user?.role.equals("customer"))
            startActivity(intentFor<CustomerMainActivity>())
        else if(UserStorage.user?.role.equals("admin"))
            startActivity(intentFor<AdminMainActivity>())
    }

    fun loginWithFacebook()
    {
        startActivity(intentFor<FacebookLoginActivity>())
    }

    fun loginWithGoogle()
    {
        startActivity(intentFor<GoogleLoginActivity>())
    }
}
