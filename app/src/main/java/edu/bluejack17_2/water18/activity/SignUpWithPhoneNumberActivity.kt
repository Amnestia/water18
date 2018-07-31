package edu.bluejack17_2.water18.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.firebase.PhoneNumberAuth
import kotlinx.android.synthetic.main.activity_sign_up_with_phone_number.*

class SignUpWithPhoneNumberActivity : Activity(), View.OnClickListener
{
    private fun addListener()
    {
        val buttons= arrayOf(btnSignUp)
        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_with_phone_number)
        addListener()
    }

    override fun onClick(src: View?)
    {
        when(src)
        {
            btnSignUp->
            {
                signUp()
            }
            else->return
        }
    }

    fun signUp()
    {
        val phone=tfPhoneNumber.text.toString()
        PhoneNumberAuth.verifyPhoneNumber(phone,this)
    }
}
