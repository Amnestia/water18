package edu.bluejack17_2.water18.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.activity.LoginActivity
import edu.bluejack17_2.water18.storage.NotificationIDStorage

object NotificationExecutor
{
    fun notif(context: Context,tag:String,name:String)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("Notification", "Water18 Channel Name", importance)
            channel.description = "Water18 Channel Description"
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val notif=
            NotificationCompat.Builder(context,"Notification")
                    .setSmallIcon(R.drawable.ic_app_notif_silhouette)
                    .setContentTitle(tag)
                    .setContentText(getContentText(tag,name))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setContentIntent(PendingIntent.getActivity(context,0, Intent(context,LoginActivity::class.java),0))
                    .setAutoCancel(true)
        NotificationManagerCompat.from(context).notify(NotificationIDStorage.generateID(),notif.build())
    }

    fun getContentText(tag: String,name: String): String
    {
        return when(tag)
        {
            "Price update"-> "Price of $name has been updated!"
            "Out of stock"-> "$name is out of stock"
            "Read"->"Your transaction has been read by admin"
            "New item"->"New item added"
            "Stock update"-> "Stock of $name has been updated"
             else -> ""
        }
    }
}