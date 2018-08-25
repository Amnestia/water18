package edu.bluejack17_2.water18.baseclass

import com.google.firebase.database.DataSnapshot


interface OnGetDataListener
{
    fun onSuccess(snapshot: DataSnapshot)
}