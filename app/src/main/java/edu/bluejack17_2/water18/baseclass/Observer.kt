package edu.bluejack17_2.water18.baseclass

import android.content.Context

interface Observer
{
    fun update(ctx:Context,tag:String,name:String)
    fun update(uid:String,ctx:Context,tag:String,name:String)
}