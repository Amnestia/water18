package edu.bluejack17_2.water18.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import edu.bluejack17_2.water18.R

class ChangePasswordFragment : Fragment(), View.OnClickListener
{
    companion object
    {
        fun newInstance(): ChangePasswordFragment
        {
            return ChangePasswordFragment()
        }
    }

    private fun addListener()
    {
        //        var buttons=arrayOf(button2)
        //       buttons.forEach { it.setOnClickListener(this)  }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let{

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onClick(src: View?)
    {

    }
}
