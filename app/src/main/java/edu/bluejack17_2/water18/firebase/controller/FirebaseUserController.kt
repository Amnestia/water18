package edu.bluejack17_2.water18.firebase.controller

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseUserController
{
    val auth=FirebaseAuth.getInstance()

    fun getCurrentUser() : FirebaseUser?
    {
        return auth.currentUser
    }

    fun setUser(user: FirebaseUser)
    {
        FirebaseAuth.getInstance().updateCurrentUser(user)
    }

    fun delUser(user: FirebaseUser)
    {

    }

    fun signOut()
    {
        auth.signOut()
    }
}