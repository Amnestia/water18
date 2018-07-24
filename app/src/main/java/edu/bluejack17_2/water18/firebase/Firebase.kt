package edu.bluejack17_2.water18.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import edu.bluejack17_2.water18.model.User

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

    fun login(phone:String,password:String)
    {
        val db=FirebaseDatabase.getInstance().getReference("user")
    }

}

