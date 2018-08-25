package edu.bluejack17_2.water18.notification

import android.content.Context
import edu.bluejack17_2.water18.baseclass.Observable
import edu.bluejack17_2.water18.baseclass.Observer
import java.util.*

object Notifier : Observable
{
    val observer: MutableList<Observer> = Vector()

    override fun attach(obs: Observer)
    {
        observer.add(obs)
    }

    override fun detachAll()
    {
        observer.clear()
    }

    override fun notifyAllCustomer(ctx: Context, tag: String, name: String)
    {
        for(obs in observer)
            obs.update(ctx,tag,name)
    }

    override fun notifyCustomer(uid:String,ctx: Context, tag: String, name: String)
    {
        for(obs in observer)
            obs.update(uid,ctx,tag,name)
    }

}