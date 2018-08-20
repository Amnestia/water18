package edu.bluejack17_2.water18.fragment.customer

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.adapter.list.customer.OrderAdapter
import edu.bluejack17_2.water18.controller.ProductListController
import edu.bluejack17_2.water18.model.Product
import kotlinx.android.synthetic.main.fragment_order_list.*

class OrderFragment : Fragment(), View.OnClickListener
{

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    private fun addListener() = arrayOf(btn_place_order).forEach { it.setOnClickListener(this) }

    companion object
    {
        fun newInstance() = OrderFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        addListener()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_order_list, container, false)

        if(view is RecyclerView)
        {
            with(view) {
                layoutManager = when
                {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = OrderAdapter(ProductListController.items, listener)
            }
        }
        return view
    }

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        if(context is OnListFragmentInteractionListener)
        {
            listener = context
        }
        else
        {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach()
    {
        super.onDetach()
        listener = null
    }

    override fun onClick(src: View?)
    {
        when(src)
        {
            btn_place_order->placeOrder()
            else -> return
        }
    }

    private fun placeOrder()
    {

    }


    interface OnListFragmentInteractionListener
    {
        fun onListFragmentInteraction(item: Product?)
    }
}
