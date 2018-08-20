package edu.bluejack17_2.water18.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.ProductListController
import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.model.Timestamp
import kotlinx.android.synthetic.main.fragment_add_item.*
import org.jetbrains.anko.support.v4.toast

class AddItemFragment : Fragment(), View.OnClickListener
{
    companion object
    {
        fun newInstance() = AddItemFragment()
    }

    private fun addListener() = arrayOf(btn_submit).forEach { it.setOnClickListener(this)  }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let{

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        addListener()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onClick(src: View?)
    {
        when(src)
        {
            btn_submit->addItem()
            else->return
        }
    }

    fun addItem()
    {
        val name=tf_name.text.toString()
        val price=tf_price.text.toString().toLongOrNull()
        val stock=tf_quantity.text.toString().toLongOrNull()
        ProductListController.insert(Product("",name,price,stock, Timestamp()))
        tf_name.setText("", TextView.BufferType.EDITABLE)
        tf_price.setText("", TextView.BufferType.EDITABLE)
        tf_quantity.setText("", TextView.BufferType.EDITABLE)
        toast("Successfully added new item")
    }
}
