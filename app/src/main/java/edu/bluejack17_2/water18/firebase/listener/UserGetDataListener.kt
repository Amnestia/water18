package edu.bluejack17_2.water18.firebase.listener

import com.google.firebase.database.DataSnapshot
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.controller.UserController
import edu.bluejack17_2.water18.model.User

object UserGetDataListener : OnGetDataListener
{
    override fun onSuccess(snapshot: DataSnapshot)
    {
        snapshot.children.forEach {
            UserController.addCustomer(it.getValue<User>(User::class.java) as User)
            UserController.addUser(it.getValue<User>(User::class.java) as User)
        }
    }
}