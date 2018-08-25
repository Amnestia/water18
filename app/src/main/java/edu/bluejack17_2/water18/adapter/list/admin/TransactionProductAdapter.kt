package edu.bluejack17_2.water18.adapter.list.admin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.TransactionController
import edu.bluejack17_2.water18.fragment.admin.TransactionAdminFragment
import edu.bluejack17_2.water18.model.PairX
import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.model.Transaction
import edu.bluejack17_2.water18.storage.TransactionStorage
import kotlinx.android.synthetic.main.fragment_transaction_admin_view.view.*
import kotlinx.android.synthetic.main.item_transaction_admin_view.view.*

class TransactionProductAdapter(private val mValues: List<PairX<Product, Long>>,private val mListener: TransactionAdminFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<TransactionProductAdapter.ViewHolder>()
{

    private val mOnClickListener: View.OnClickListener

    private lateinit var txtTotalPrice: TextView

    init
    {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Transaction
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction_admin_view, parent, false)
        val viewList = LayoutInflater.from(parent.context).inflate(R.layout.fragment_transaction_admin_view, parent, false)
        txtTotalPrice=viewList.txt_total_price
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val item = mValues[position]
        holder.itemName.text = item.first.name
        holder.itemPrice.text = item.first.price.toString()
        holder.itemQuantity.text=   item.second.toString()
        txtTotalPrice.text=TransactionController.calculateTotalPrice(TransactionStorage.transaction).toString()

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
    {
        val itemName : TextView = mView.txt_item_name
        val itemPrice : TextView = mView.txt_item_price
        val itemQuantity : TextView = mView.txt_item_quantity
    }
}