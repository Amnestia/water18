package edu.bluejack17_2.water18.firebase.listener

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError


interface OnGetDataListener
{
    fun onStart()
    fun onSuccess(snapshot: DataSnapshot)
    fun onFailed(e: DatabaseError)
}