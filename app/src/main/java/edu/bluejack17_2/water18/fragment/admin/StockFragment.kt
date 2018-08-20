package edu.bluejack17_2.water18.fragment.admin

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
import edu.bluejack17_2.water18.controller.ProductListController
import edu.bluejack17_2.water18.fragment.AddItemFragment
import edu.bluejack17_2.water18.model.Product
import kotlinx.android.synthetic.main.fragment_stock_list.*
import kotlinx.android.synthetic.main.fragment_stock_list.view.*

class StockFragment : Fragment(), View.OnClickListener
{
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    companion object
    {
        fun newInstance() = StockFragment()
    }

    private fun addListener() = arrayOf(btn_add).forEach { it.setOnClickListener(this) }

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
        val view = inflater.inflate(R.layout.fragment_stock_list, container, false)
        if(view.list is RecyclerView)
        {
            with(view.list) {
                layoutManager = when
                {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = ProductStockAdapter(ProductListController.items, listener)
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
        var fragment:Fragment?
        when(src)
        {
            btn_add -> fragment=AddItemFragment.newInstance()
            else -> return
        }
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.content_frame,fragment as Fragment)
            commit()
        }
    }

    interface OnListFragmentInteractionListener
    {
        fun onListFragmentInteraction(item: Product?)
    }
}
