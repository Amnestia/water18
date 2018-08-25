package edu.bluejack17_2.water18.firebase

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import edu.bluejack17_2.water18.activity.EditProfileActivity
import edu.bluejack17_2.water18.activity.PhoneVerificationActivity
import edu.bluejack17_2.water18.firebase.controller.FirebaseUserController
import java.util.concurrent.TimeUnit

object PhoneNumberAuth
{
    private fun callbacks(phoneNumber: String,activity: Activity) : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    {
        return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            override fun onVerificationCompleted(credential: PhoneAuthCredential?)
            {
                signIn(credential,activity,phoneNumber)
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
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,120, TimeUnit.SECONDS,activity,callbacks(phoneNumber,activity))
    }

    fun resendCode(phoneNumber: String, token: PhoneAuthProvider.ForceResendingToken?,activity: Activity)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,120, TimeUnit.SECONDS,activity,callbacks(phoneNumber,activity),token)
    }

    fun signIn(credential: PhoneAuthCredential?, activity: Activity,phoneNumber: String): Boolean
    {
        var ret=false
        val auth=FirebaseAuth.getInstance()
        if(credential != null)
        {
            auth.signInWithCredential(credential).addOnCompleteListener(activity) { task ->
                if(task.isSuccessful)
                {
                    FirebaseUserController.setUser(task.result.user)
                    ret=true
                    val intent= Intent(activity.applicationContext, EditProfileActivity::class.java)
                    intent.putExtra("phone",phoneNumber)
                    activity.startActivity(intent)
                }
                else
                {
                    Toast.makeText(activity.applicationContext,"Sign in failed",Toast.LENGTH_SHORT).show()
                }
            }
        }
        return ret

    }
}