package edu.bluejack17_2.water18.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import edu.bluejack17_2.water18.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : Activity(), View.OnClickListener
{

    private fun addListener()
    {
        val buttons=arrayOf(btn_login ,btn_login_facebook ,btn_login_google ,btn_sign_up)
        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)
        addListener()
    }

    override fun onClick(src: View?)
    {
        when(src){
            btn_login->
            {
                val intent=Intent(applicationContext,SignUpWithPhoneNumberActivity::class.java)
                startActivity(intent)
            }
            btn_login->
            {
                val phone=tf_phone_login.text
                val password=pf_password.text
            }
            else -> return
        }
    }
}
