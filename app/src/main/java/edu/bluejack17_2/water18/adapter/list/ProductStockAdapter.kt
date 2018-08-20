package edu.bluejack17_2.water18.adapter.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.ProductListController
import edu.bluejack17_2.water18.fragment.admin.StockFragment
import edu.bluejack17_2.water18.model.Product
import kotlinx.android.synthetic.main.item_stock.view.*

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val item = mValues[position]
        holder.itemName.setText(item.name,TextView.BufferType.EDITABLE)
        holder.itemPrice.setText(item.price.toString(),TextView.BufferType.EDITABLE)
        holder.itemQuantity.setText(item.stock.toString(),TextView.BufferType.EDITABLE)
        holder.buttons[0].setOnClickListener{deleteItem(item)}
        holder.buttons[1].setOnClickListener{updateItem(item,holder)}
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
    {
        val itemName : EditText = mView.tf_item_name
        val itemPrice : EditText = mView.tf_item_price
        val itemQuantity : EditText = mView.tf_quantity
        val buttons= arrayOf(mView.btn_delete,mView.btn_save)
    }

    fun deleteItem(item : Product)
    {
        ProductListController.delete(item)
    }

    fun updateItem(item : Product, holder: ViewHolder)
    {
        item.name=holder.itemName.text.toString()
        item.price=holder.itemPrice.text.toString().toLongOrNull()
        item.stock=holder.itemQuantity.text.toString().toLongOrNull()
        ProductListController.update(item)
    }
}