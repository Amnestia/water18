package edu.bluejack17_2.water18.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.ProductController
import edu.bluejack17_2.water18.model.Notification
import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.model.Timestamp
import edu.bluejack17_2.water18.notification.controller.NotificationController
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
        var price=tf_price.text.toString().toLongOrNull()
        var stock=tf_quantity.text.toString().toLongOrNull()
        val ret=validate(name, price, stock)
        if(!ret.equals("Clear"))
        {
            toast(ret)
            return
        }
        ProductController.insert(Product("",name,price,stock, Timestamp()))
        tf_name.setText("", TextView.BufferType.EDITABLE)
        tf_price.setText("", TextView.BufferType.EDITABLE)
        tf_quantity.setText("", TextView.BufferType.EDITABLE)
        toast("Successfully added new item")
        NotificationController.insert(Notification("","New item","","", Timestamp()))
    }

    fun validate(name:String,price:Long?,stock:Long?):String
    {
        return when
        {
            name.isNullOrEmpty()->"Name should not be empty"
            price==null->"Price should not be empty"
            price<0->"Price should not be negative"
            stock==null->"Stock should not be empty"
            stock<0->"Stock should not be negative"
            else->"Clear"
        }
    }

}
