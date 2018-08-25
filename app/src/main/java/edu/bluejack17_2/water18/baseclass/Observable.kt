package edu.bluejack17_2.water18.baseclass

import android.content.Context

interface Observable
{
    fun attach(observer: Observer)
    fun detachAll()
    fun notifyAllCustomer(ctx: Context, tag:String, name:String)
    fun notifyCustomer(uid:String ,ctx: Context,tag: String,name: String)
}