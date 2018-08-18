package edu.bluejack17_2.water18.adapter.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.fragment.admin.StockFragment
import edu.bluejack17_2.water18.model.Product
import kotlinx.android.synthetic.main.fragment_order.view.*

class ProductStockAdapter(private val mValues: List<Product>, private val mListener: StockFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<ProductStockAdapter.ViewHolder>()
{

    private val mOnClickListener: View.OnClickListener

    init
    {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Product
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val item = mValues[position]
        holder.itemName.text = item.name
        holder.itemPrice.text = item.price.toString()

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
    }
}