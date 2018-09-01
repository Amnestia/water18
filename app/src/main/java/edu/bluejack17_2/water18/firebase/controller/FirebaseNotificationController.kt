package edu.bluejack17_2.water18.firebase.controller

import edu.bluejack17_2.water18.firebase.Firebase
import edu.bluejack17_2.water18.model.Notification
import java.util.*

object FirebaseNotificationController
{
    val db = Firebase.getReference("notification")

    fun insertNotification(notif:Notification)
    {
        val id= db.push().key
        notif.id= id!!
        db.child(id).setValue(notif)
    }

    fun deleteNotification(notif: Notification)
    {
        val id=notif.id
        db.child(id).child("timestamp").child("deleted_at").setValue(Date().toString())
    }

}