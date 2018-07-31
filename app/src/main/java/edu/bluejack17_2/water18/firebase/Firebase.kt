package edu.bluejack17_2.water18.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object Firebase
{
    fun getCurrentUser() : FirebaseUser?
    {
        var mAuth = FirebaseAuth.getInstance()
        return mAuth.currentUser
    }

    fun setUser(user: FirebaseUser)
    {
        FirebaseAuth.getInstance().updateCurrentUser(user)
    }

}

