package edu.bluejack17_2.water18.notification.controller

import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.firebase.controller.FirebaseNotificationController
import edu.bluejack17_2.water18.model.Notification
import edu.bluejack17_2.water18.notification.Notifier
import edu.bluejack17_2.water18.service.NotificationService

object NotificationController
{
    fun execute(notif: Notification)
    {
        if(notif.timestamp.deleted_at.isNullOrEmpty())
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

    fun read(gdl:OnGetDataListener)
    {
        FirebaseNotificationController.db.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                gdl.onSuccess(snapshot)
            }

            override fun onCancelled(e: DatabaseError)
            {
                Log.w("DBError",e.toString())
            }
        })
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