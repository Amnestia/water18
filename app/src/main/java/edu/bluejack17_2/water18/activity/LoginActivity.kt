package edu.bluejack17_2.water18.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.firebase.Firebase
import edu.bluejack17_2.water18.utility.Hash
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : Activity(), View.OnClickListener
{

    private fun addListener()
    {
        val buttons=arrayOf(btnLogin,btnLoginFacebook,btnLoginGoogle,btnSignUp)
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
            btnSignUp->
            {
                val intent=Intent(applicationContext,SignUpActivity::class.java)
                startActivity(intent)
            }
            btnLogin->
            {
                val phone=tfPhoneLogin.text
                val password=pfPassword.text
                Firebase.login(phone.toString(),Hash.hashSHA512(password.toString()).toString())
            }
            else -> return
        }
    }
}
