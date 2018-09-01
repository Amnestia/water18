package edu.bluejack17_2.water18.notification.controller

import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.bluejack17_2.water18.firebase.controller.FirebaseNotificationController
import edu.bluejack17_2.water18.firebase.listener.NotificationGetDataListener
import edu.bluejack17_2.water18.model.Notification
import edu.bluejack17_2.water18.notification.Notifier

object NotificationController
{
    fun execute(notif: Notification,ctx:Context)
    {
        if(notif.timestamp.deleted_at.isNullOrEmpty())
        {
            when(notif.tag)
            {
                "Read"-> callNotifier(notif.user,ctx, notif.tag, "")
                "New item" -> callNotifier(notif.user, ctx, notif.tag, "")
                else -> callNotifier(notif.user, ctx, notif.tag, notif.item!!)
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

    fun read(gdl:NotificationGetDataListener,ctx: Context)
    {
        FirebaseNotificationController.db.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                gdl.attachContext(ctx)
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