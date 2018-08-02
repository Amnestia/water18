package edu.bluejack17_2.water18.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import edu.bluejack17_2.water18.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener
{

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
    }

    override fun onClick(src: View?)
    {
        when(src){
            btn_sign_up->
            {
                val intent=Intent(applicationContext,SignUpWithPhoneNumberActivity::class.java)
                startActivity(intent)
            }
            btn_login->
            {
                val phone=tf_phone_login.text
                val password=pf_password.text
                val intent=Intent(applicationContext,HomeActivity::class.java)
                startActivity(intent)
            }
            btn_login_facebook->
            {

            }
            else -> return
        }
    }
}
