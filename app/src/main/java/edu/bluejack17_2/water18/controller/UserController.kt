package edu.bluejack17_2.water18.controller

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.firebase.controller.FirebaseUserController
import edu.bluejack17_2.water18.model.User
import edu.bluejack17_2.water18.notification.Notifier
import edu.bluejack17_2.water18.storage.UserStorage
import edu.bluejack17_2.water18.utility.Hash

object UserController
{
    fun addCustomer(user: User)
    {
        if(user.role.equals("customer"))
        {
            Notifier.attach(user)
        }
    }

    fun addUser(user:User)
    {
        UserStorage.users.add(user)
    }

    fun read(gdl: OnGetDataListener)
    {
        FirebaseUserController.db.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                Notifier.detachAll()
                gdl.onSuccess(snapshot)
            }

            override fun onCancelled(e: DatabaseError)
            {
                Log.w("DBError",e.toString())
            }
        })
    }

    fun insert(user: User)
    {
        FirebaseUserController.insertUser(user)
    }

    fun update(user: User)
    {
        FirebaseUserController.updateUser(user)
    }

    fun searchUser(id:String):User?
    {
        for(u in UserStorage.users)
            if(u.id.equals(id))
                return u
        return null
    }

    fun getUser(id:String):User?
    {
        return searchUser(id)
    }

    fun existed(phoneNumber: String):Boolean
    {
        for(u in UserStorage.users)
            if(u.phoneNumber.equals(phoneNumber))
                return true
        return false
    }

    fun login(phoneNumber:String,password:String) : User?
    {
        val hashed= Hash.hashSHA512(password)
        for(u in UserStorage.users)
            if(u.phoneNumber.equals(phoneNumber) && u.password.equals(hashed))
                return u
        return null
    }
}