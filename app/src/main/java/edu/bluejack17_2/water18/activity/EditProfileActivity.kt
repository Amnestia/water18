package edu.bluejack17_2.water18.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.UserController
import edu.bluejack17_2.water18.firebase.controller.FirebaseUserController
import edu.bluejack17_2.water18.model.Timestamp
import edu.bluejack17_2.water18.model.User
import edu.bluejack17_2.water18.storage.UserStorage
import edu.bluejack17_2.water18.utility.Hash
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class EditProfileActivity : AppCompatActivity(), View.OnClickListener
{
    private fun addListener() = arrayOf(btn_submit).forEach { it.setOnClickListener(this)  }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val phone=intent?.extras!!.get("phone")
        if(phone!=null)
        {
            tf_phone.setText(phone.toString(),TextView.BufferType.EDITABLE)
            tf_phone.isEnabled = false
        }
        addListener()
    }

    override fun onClick(src: View?)
    {
        when(src)
        {
            btn_submit-> submit()
            else->return
        }
    }

    fun submit()
    {
        val uid=FirebaseUserController.getCurrentUser()?.uid
        val address=tf_address.text.toString()
        val phone=tf_phone.text.toString()
        val password=pf_password.text.toString()
        val confirmPassword=pf_confirm_password.text.toString()
        Log.w("asda",password+" "+confirmPassword+" "+(password.equals(confirmPassword)).toString())
        val ret=validate(phone,address,password,confirmPassword)
        if(!ret.equals("Clear"))
        {
            toast(ret)
            return
        }
        val user=User(uid as String,address,phone,Hash.hashSHA512(password),"customer", Timestamp())
        UserController.insert(user)
        UserStorage.user=user
        startActivity(intentFor<CustomerMainActivity>())
    }

    fun validate(phone:String,address:String,password:String,confirmPassword:String):String
    {
        return when{
            phone.isEmpty()->"Please enter your phone number"
            address.isEmpty()->"Please enter your addresss"
            !password.equals(confirmPassword)->"Password does not matched"
            else -> "Clear"
        }
    }
}