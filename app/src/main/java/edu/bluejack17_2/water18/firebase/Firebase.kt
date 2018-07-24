package edu.bluejack17_2.water18.firebase

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import edu.bluejack17_2.water18.model.User
import java.util.concurrent.TimeUnit

object Firebase
{
    fun getCurrentUser() : FirebaseUser?
    {
        var mAuth = FirebaseAuth.getInstance()
        return mAuth.currentUser
    }

    fun insertUser(u: User)
    {
        val db=FirebaseDatabase.getInstance().getReference("user")
        db.child(u.phoneNumber.toString()).setValue(u)
    }

    fun verifyPhoneNumber(phoneNumber: String,activity: Activity)
    {
        val callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            override fun onVerificationCompleted(credential: PhoneAuthCredential?)
            {

            }

            override fun onVerificationFailed(p0: FirebaseException?)
            {

            }
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,300,TimeUnit.SECONDS,activity,callback)

    }


}

