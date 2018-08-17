package edu.bluejack17_2.water18.adapter.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.fragment.customer.tab.view.HistoryFragment
import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.model.TransactionHistory
import kotlinx.android.synthetic.main.fragment_history.view.*

class TransactionHistoryAdapter(private val mValues: List<Product>, private val mListener: HistoryFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>()
{

    private val mOnClickListener: View.OnClickListener

    init
    {
        mOnClickListener = View.OnClickListener { v ->
            val history = v.tag as TransactionHistory
            mListener?.onListFragmentInteraction(history)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val history = mValues[position]
        holder.historyName.text = history.name
        holder.historyPrice.text = history.price.toString()

        with(holder.mView) {
            tag = history
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
    {
        val historyName : TextView = mView.txt_transaction_date
        val historyPrice : TextView = mView.txt_transaction_total_price
    }
}
