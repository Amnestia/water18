package edu.bluejack17_2.water18.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.firebase.PhoneNumberAuth
import kotlinx.android.synthetic.main.activity_sign_up_with_phone_number.*
import org.jetbrains.anko.toast

class SignUpWithPhoneNumberActivity : AppCompatActivity(), View.OnClickListener
{
    private fun addListener() = arrayOf(btn_sign_up).forEach { it.setOnClickListener(this)  }

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
            btn_sign_up -> signUp()
            else->return
        }
    }

    fun signUp()
    {
        val phone=tfPhoneNumber.text.toString()
        if(phone.isEmpty())
        {
            toast("Please enter your phone number!")
            return
        }
        PhoneNumberAuth.verifyPhoneNumber(phone,this)
    }
}
