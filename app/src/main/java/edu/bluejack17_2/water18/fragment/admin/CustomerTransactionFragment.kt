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
import edu.bluejack17_2.water18.adapter.list.admin.TransactionAdapter
import edu.bluejack17_2.water18.controller.TransactionController
import edu.bluejack17_2.water18.model.Transaction
import kotlinx.android.synthetic.main.fragment_order_admin.view.*

class CustomerTransactionFragment : Fragment()
{
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    companion object
    {
        fun newInstance() = CustomerTransactionFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_order_admin, container, false)

        if(view.list is RecyclerView)
        {
            with(view.list) {
                layoutManager = when
                {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = TransactionAdapter(TransactionController.transactions, listener)
            }
            view.list.adapter?.notifyDataSetChanged()
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

    interface OnListFragmentInteractionListener
    {
        fun onListFragmentInteraction(transaction: Transaction)
    }
}