package edu.bluejack17_2.water18.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import edu.bluejack17_2.water18.model.Notification
import edu.bluejack17_2.water18.notification.controller.NotificationController

class NotificationService(val notif: Notification?) : Service()
{
    constructor():this(Notification())

    override fun onBind(p0: Intent?): IBinder?
    {
        return null
    }

    override fun onCreate()
    {
        super.onCreate()
        callNotification()
    }

    fun callNotification()
    {
        Log.w("dasdasasdas","qweqwe")
        when(notif!!.tag)
        {
            "Read"-> NotificationController.callNotifier(notif!!.user,this, notif!!.tag, "")
            "New item" -> NotificationController.callNotifier(notif!!.user, this, notif!!.tag, "")
            ""->return
            else -> NotificationController.callNotifier(notif!!.user, this, notif!!.tag, notif!!.item!!)
        }
    }

}