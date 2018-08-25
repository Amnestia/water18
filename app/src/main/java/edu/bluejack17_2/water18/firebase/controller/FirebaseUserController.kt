package edu.bluejack17_2.water18.firebase.controller

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import edu.bluejack17_2.water18.model.User
import java.util.*

object FirebaseUserController
{
    val auth=FirebaseAuth.getInstance()
    val db=FirebaseDatabase.getInstance().getReference("user")

    fun getCurrentUser() : FirebaseUser?
    {
        return auth.currentUser
    }

    fun setUser(user: FirebaseUser)
    {
        FirebaseAuth.getInstance().updateCurrentUser(user)
    }

    fun insertUser(user: User)
    {
        db.child(user.id).setValue(user)
    }

    fun updateUser(user: User)
    {
        user.timestamp.updated_at= Date().toString()
        db.child(user.id).setValue(user)
    }

    fun signOut()
    {
        auth.signOut()
    }
}