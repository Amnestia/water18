package edu.bluejack17_2.water18.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import edu.bluejack17_2.water18.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), View.OnClickListener
{

    companion object
    {
        fun newInstance() = HomeFragment()
    }

    private fun addListener()
    {
        var buttons=arrayOf(btnChangePassword,btnEdit,btnSave)
        buttons.forEach { it.setOnClickListener(this)  }
    }

    private fun showMode()
    {
        btnEdit.visibility=Button.VISIBLE
        btnSave.visibility=Button.INVISIBLE
        tf_user_address.visibility=EditText.INVISIBLE
        txt_user_address.visibility=TextView.VISIBLE
        txt_user_address.text=tf_user_address.text
    }

    private fun editMode()
    {
        btnEdit.visibility=Button.INVISIBLE
        btnSave.visibility=Button.VISIBLE
        tf_user_address.setText(txt_user_address.text,TextView.BufferType.EDITABLE)
        tf_user_address.visibility=EditText.VISIBLE
        txt_user_address.visibility=TextView.INVISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let{

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        Log.w("Home","H")
        addListener()
        showMode()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onClick(src: View?)
    {
        when(src)
        {
            btnChangePassword->changePassword()
            btnEdit->edit()
            btnSave->save()
            else->return
        }
    }

    fun changePassword()
    {

    }

    fun save()
    {
        showMode()
        updateAddress(tf_user_address.text.toString())
    }

    fun edit()
    {
        editMode()
    }

    fun updateAddress(address: String?)
    {
        
    }

}
