package edu.bluejack17_2.water18.model

import android.content.Context
import edu.bluejack17_2.water18.baseclass.Observer
import edu.bluejack17_2.water18.notification.NotificationExecutor
import edu.bluejack17_2.water18.storage.UserStorage


data class User(var id: String, var address: String?,
           var phoneNumber: String?, var password: String?,var role: String?,val timestamp: Timestamp) : Observer
{

    override fun update(ctx: Context, tag: String, name: String)
    {
        NotificationExecutor.notif(ctx,tag,name)
    }

    override fun update(uid: String, ctx: Context, tag: String, name: String)
    {
        if(UserStorage.user?.id.equals(uid))
        NotificationExecutor.notif(ctx,tag,name)
    }

    override fun toString(): String = id
    constructor():this("","","","","",Timestamp())
}
