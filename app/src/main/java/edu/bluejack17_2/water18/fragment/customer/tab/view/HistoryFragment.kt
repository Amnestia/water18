package edu.bluejack17_2.water18.fragment.customer.tab.view

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
import edu.bluejack17_2.water18.adapter.list.customer.TransactionHistoryAdapter
import edu.bluejack17_2.water18.controller.TransactionHistoryListController
import edu.bluejack17_2.water18.model.TransactionHistory

class HistoryFragment : Fragment()
{
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    companion object
    {
        fun newInstance() = HistoryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_history_list, container, false)

        if(view is RecyclerView)
        {
            with(view) {
                layoutManager = when
                {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = TransactionHistoryAdapter(TransactionHistoryListController.items, listener)
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

    interface OnListFragmentInteractionListener
    {
        fun onListFragmentInteraction(history: TransactionHistory?)
    }
}
