package edu.bluejack17_2.water18.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object Firebase
{
    fun getCurrentUser() : FirebaseUser?
    {
        return FirebaseAuth.getInstance().currentUser
    }

    fun setUser(user: FirebaseUser)
    {
        FirebaseAuth.getInstance().updateCurrentUser(user)
    }

}

