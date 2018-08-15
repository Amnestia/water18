package edu.bluejack17_2.water18.firebase.controller

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseUserController
{
    fun getCurrentUser() : FirebaseUser?
    {
        return FirebaseAuth.getInstance().currentUser
    }

    fun setUser(user: FirebaseUser)
    {
        FirebaseAuth.getInstance().updateCurrentUser(user)
    }

    fun delUser(user: FirebaseUser)
    {

    }
}