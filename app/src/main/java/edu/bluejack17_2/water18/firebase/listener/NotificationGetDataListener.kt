package edu.bluejack17_2.water18.firebase.listener

import com.google.firebase.database.DataSnapshot
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.model.Notification
import edu.bluejack17_2.water18.notification.controller.NotificationController

object NotificationGetDataListener: OnGetDataListener
{
    override fun onSuccess(snapshot: DataSnapshot)
    {
        snapshot.children.forEach { NotificationController.execute(it.getValue<Notification>(Notification::class.java) as Notification) }
    }
}