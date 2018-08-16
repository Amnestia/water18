package edu.bluejack17_2.water18.fragment.admin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.bluejack17_2.water18.R

class StockFragment : Fragment(), View.OnClickListener
{
    companion object
    {
        fun newInstance() = StockFragment()
    }

    private fun addListener()
    {
        //var buttons=arrayOf(button2)
        //buttons.forEach { it.setOnClickListener(this)  }
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
        addListener()
        return inflater.inflate(R.layout.fragment_stock, container, false)
    }

    override fun onClick(src: View?)
    {

    }
}
