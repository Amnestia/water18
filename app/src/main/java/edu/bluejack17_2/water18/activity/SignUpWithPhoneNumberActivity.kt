package edu.bluejack17_2.water18.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.UserController
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
        val ret=validate(phone)
        if(!ret.equals("Clear"))
        {
            toast(ret)
            return
        }
        PhoneNumberAuth.verifyPhoneNumber(phone,this)
    }

    fun validate(phone:String):String
    {
        return when
        {
            phone.isEmpty()->"Please enter your phone number!"
            !phone.startsWith("+")->"Please use +(country code) format"
            UserController.existed(phone)->"Phone number has already been registered"
            else -> "Clear"
        }

    }
}
