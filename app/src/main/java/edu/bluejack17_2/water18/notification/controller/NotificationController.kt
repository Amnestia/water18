package edu.bluejack17_2.water18.notification.controller

import android.content.Context
import edu.bluejack17_2.water18.firebase.controller.FirebaseNotificationController
import edu.bluejack17_2.water18.model.Notification
import edu.bluejack17_2.water18.notification.Notifier
import edu.bluejack17_2.water18.service.NotificationService

object NotificationController
{
    fun execute(notif: Notification)
    {
        if(!notif.timestamp.deleted_at.isNullOrEmpty())
        {
            when(notif.tag)
            {
                "Read"-> callNotifier(notif.user,NotificationService(),notif.tag,"")
                "New item" -> callNotifier(notif.user,NotificationService(),notif.tag,"")
                else -> callNotifier(notif.user,NotificationService(),notif.tag,notif.item!!)
            }
            del(notif)
        }
    }

    fun callNotifier(id:String?,ctx:Context,tag:String,name:String)
    {
        if(id.isNullOrEmpty())
        Notifier.notifyAllCustomer(ctx,tag,name)
        else
        Notifier.notifyCustomer(id!!,ctx,tag,name)
    }

    fun insert(notif:Notification)
    {
        FirebaseNotificationController.insertNotification(notif)
    }

    fun del(notif: Notification)
    {
        FirebaseNotificationController.deleteNotification(notif)
    }
}