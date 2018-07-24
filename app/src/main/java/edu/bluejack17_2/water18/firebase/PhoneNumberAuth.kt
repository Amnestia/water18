package edu.bluejack17_2.water18.firebase

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

object PhoneNumberAuth
{
    fun verifyPhoneNumber(phoneNumber: String,activity: Activity)
    {
        val callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            override fun onVerificationCompleted(credential: PhoneAuthCredential?)
            {

            }

            override fun onVerificationFailed(e: FirebaseException?)
            {

            }
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,300, TimeUnit.SECONDS,activity,callback)
    }
}