package edu.bluejack17_2.water18.adapter.list.admin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.TransactionController
import edu.bluejack17_2.water18.fragment.admin.CustomerTransactionFragment
import edu.bluejack17_2.water18.model.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionAdapter(private val mValues: List<Transaction>,private val mListener: CustomerTransactionFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>()
{
    private val mOnClickListener: View.OnClickListener

    private lateinit var ctx: Context

    init
    {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Transaction
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        ctx=parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val transaction = mValues[position]
        holder.transactionDate.text = transaction.date
        holder.transactionTotalPrice.text = TransactionController.calculateTotalPrice(transaction).toString()
        holder.transactionPhone.text=transaction.user.phoneNumber
        with(holder.mView) {
            tag = transaction
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
    {
        val transactionDate : TextView = mView.txt_transaction_date
        val transactionTotalPrice : TextView = mView.txt_transaction_total_price
        val transactionPhone: TextView=mView.txt_user_phone
    }
}