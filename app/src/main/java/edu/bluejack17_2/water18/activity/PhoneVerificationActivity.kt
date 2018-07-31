package edu.bluejack17_2.water18.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.PhoneAuthProvider
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.firebase.PhoneNumberAuth
import kotlinx.android.synthetic.main.activity_phone_verification.*

class PhoneVerificationActivity : Activity(), View.OnClickListener
{
    private fun addListener()
    {
        val buttons= arrayOf(btn_login, btnResend)
        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)
        addListener()
    }

    override fun onClick(src: View?)
    {
        when(src)
        {
            btn_login->
            {
                confirm()
            }
            btnResend->
            {
                resend()
            }
            else->return
        }
    }

    fun resend()
    {
        val token = intent.extras.get("token")
        val phoneNumber=intent.getStringExtra("phoneNumber")
        PhoneNumberAuth.resendCode(phoneNumber, token as PhoneAuthProvider.ForceResendingToken?,this)
    }

    fun confirm()
    {
        val verificationCode=intent.getStringExtra("verificationCode")
        val code = tfVerificationCode.text.toString()
        val credential=PhoneAuthProvider.getCredential(verificationCode,code)
        if(!PhoneNumberAuth.signIn(credential,this))
        {
            resend()
        }
        else
        {
            val intent= Intent(applicationContext,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
