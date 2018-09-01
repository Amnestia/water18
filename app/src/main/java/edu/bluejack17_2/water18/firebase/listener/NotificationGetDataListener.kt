package edu.bluejack17_2.water18.firebase.listener

import android.content.Context

import com.google.firebase.database.DataSnapshot
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.model.Notification
import edu.bluejack17_2.water18.notification.controller.NotificationController

class NotificationGetDataListener: OnGetDataListener
{
    private lateinit var context:Context

    override fun onSuccess(snapshot: DataSnapshot)
    {
        snapshot.children.forEach { NotificationController.execute(it.getValue<Notification>(Notification::class.java) as Notification,context) }
    }

    fun attachContext(ctx:Context)
    {
        context=ctx
    }
}