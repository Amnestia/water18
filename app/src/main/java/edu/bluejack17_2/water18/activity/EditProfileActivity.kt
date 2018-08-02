package edu.bluejack17_2.water18.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.model.User
import edu.bluejack17_2.water18.utility.Hash
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity(), View.OnClickListener
{
    private fun addListener()
    {
        val buttons= arrayOf(btn_submit)
        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        addListener()
    }

    override fun onClick(src: View?)
    {
        when(src)
        {
            btn_submit->
            {
                submitUserData()
            }
            else -> return
        }
    }

    fun submitUserData()
    {
        val name=tf_name.text.toString()
        val address=tf_address.text.toString()
        val password=pf_password.text.toString()
        val conPass=pf_confirm_password.text.toString()
        val phone="1"//tfPhone.text.toString()

        val ret=validate(name, address, phone, password, conPass)
        if(!ret.equals("1"))
        {
            Toast.makeText(this,ret,Toast.LENGTH_SHORT).show()
            return
        }

        val user= User(name,address,phone, Hash.hashSHA512(password))

        val success="Your account has been successfully registered!"
        Toast.makeText(this,success,Toast.LENGTH_SHORT).show()

        val intent= Intent(applicationContext,LoginActivity::class.java)
        startActivity(intent)
    }

    fun validate(name: String, address: String,
                 phone: String, password: String, conPass: String) : String?
    {
        return when{
            name.isEmpty() -> "Please enter your name!"
            address.isEmpty() -> "Please enter your address!"
            phone.isEmpty() -> "Please enter your phone number!"
            password.length<10 -> "Password must be at least 10 characters"
            !password.equals(conPass) -> "Password does not matched!"
            else -> "1"
        }

    }
}
