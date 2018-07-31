package edu.bluejack17_2.water18.firebase

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import edu.bluejack17_2.water18.activity.PhoneVerificationActivity
import java.util.concurrent.TimeUnit

object PhoneNumberAuth
{
    private fun callbacks(phoneNumber: String,activity: Activity) : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    {
        return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            override fun onVerificationCompleted(credential: PhoneAuthCredential?)
            {
                signIn(credential,activity)
            }

            override fun onVerificationFailed(e: FirebaseException?)
            {
                Log.w("Exception",e.toString())
            }

            override fun onCodeSent(verificationCode: String?, token: PhoneAuthProvider.ForceResendingToken?)
            {
                super.onCodeSent(verificationCode, token)

                val intent= Intent(activity.applicationContext, PhoneVerificationActivity::class.java)
                intent.putExtra("verificationCode",verificationCode)
                intent.putExtra("token",token)
                intent.putExtra("phoneNumber",phoneNumber)
                activity.startActivity(intent)
            }
        }
    }

    fun verifyPhoneNumber(phoneNumber: String,activity: Activity)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,300, TimeUnit.SECONDS,activity,callbacks(phoneNumber,activity))
    }

    fun resendCode(phoneNumber: String, token: PhoneAuthProvider.ForceResendingToken?,activity: Activity)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,300, TimeUnit.SECONDS,activity,callbacks(phoneNumber,activity),token)
    }

    fun signIn(credential: PhoneAuthCredential?, activity: Activity): Boolean
    {
        var ret=false
        if(credential != null)
        {
            val auth=FirebaseAuth.getInstance()
            auth.signInWithCredential(credential).addOnCompleteListener(activity) { task ->
                if(task.isSuccessful)
                {
                    Firebase.setUser(task.result.user)
                }
                else
                {

                }
            }

            ret=true
        }
        return ret

    }
}