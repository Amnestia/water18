package edu.bluejack17_2.water18.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class NotificationService : Service()
{
    override fun onBind(p0: Intent?): IBinder?
    {
        return null
    }

    override fun onCreate()
    {
        super.onCreate()
    }

}