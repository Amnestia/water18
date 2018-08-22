package edu.bluejack17_2.water18.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.facebook.CallbackManager
import edu.bluejack17_2.water18.R
import kotlinx.android.synthetic.main.activity_login.*
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback



class LoginActivity : AppCompatActivity(), View.OnClickListener
{

    val callbackManager = CallbackManager.Factory.create();

    private fun addListener()
    {
        val buttons=arrayOf(btn_login ,btn_login_facebook ,btn_login_google ,btn_sign_up)
        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addListener()

        btn_login_facebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // App code
                val intent=Intent(applicationContext,CustomerMainActivity::class.java)
                startActivity(intent)
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })
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
        //val intent=Intent(applicationContext,SignUpWithPhoneNumberActivity::class.java)
        val intent=Intent(applicationContext,CustomerMainActivity::class.java)
        startActivity(intent)
    }

    fun login()
    {
        val phone=tf_phone_login.text
        val password=pf_password.text
        //val intent=Intent(applicationContext,CustomerMainActivity::class.java)
        val intent=Intent(applicationContext,AdminMainActivity::class.java)
        startActivity(intent)
    }

    fun loginWithFacebook()
    {

    }

    fun loginWithGoogle()
    {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
