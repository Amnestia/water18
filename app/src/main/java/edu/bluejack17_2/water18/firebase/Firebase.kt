package edu.bluejack17_2.water18.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object Firebase
{
    fun getReference(db: String):DatabaseReference
    {
        return FirebaseDatabase.getInstance().getReference(db)
    }
}

