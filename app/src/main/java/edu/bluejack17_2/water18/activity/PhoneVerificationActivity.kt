package edu.bluejack17_2.water18.activity

import android.app.Activity
import android.os.Bundle
import edu.bluejack17_2.water18.R

class PhoneVerificationActivity : Activity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)
        val verificationCode = intent.getStringExtra("verificationCode")
        val token = intent.getStringExtra("token")
    }
}
