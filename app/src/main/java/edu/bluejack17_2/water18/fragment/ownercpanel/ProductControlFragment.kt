package edu.bluejack17_2.water18.fragment.ownercpanel

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
import edu.bluejack17_2.water18.adapter.list.ProductStockAdapter
import edu.bluejack17_2.water18.controller.ProductController
import edu.bluejack17_2.water18.fragment.admin.StockFragment

class ProductControlFragment : Fragment()
{
    private var columnCount = 1

    private var listener: StockFragment.OnListFragmentInteractionListener? = null

    companion object
    {
        fun newInstance() = ProductControlFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_product_control_list, container, false)

        if(view is RecyclerView)
        {
            with(view) {
                layoutManager = when
                {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = ProductStockAdapter(ProductController.items, listener)
            }
        }
        return view
    }

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        if(context is StockFragment.OnListFragmentInteractionListener)
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
}