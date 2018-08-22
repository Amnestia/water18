package edu.bluejack17_2.water18.adapter.list.customer


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.CartController
import edu.bluejack17_2.water18.fragment.customer.OrderFragment
import edu.bluejack17_2.water18.model.Product
import kotlinx.android.synthetic.main.fragment_order_list.view.*
import kotlinx.android.synthetic.main.item_order.view.*

class OrderAdapter(private val mValues: List<Product>, private val mListener: OrderFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<OrderAdapter.ViewHolder>()
{

    private val mOnClickListener: View.OnClickListener

    private lateinit var txtTotalPrice:TextView
    init
    {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Product
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        val viewList = LayoutInflater.from(parent.context).inflate(R.layout.fragment_order_list, parent, false)
        txtTotalPrice=viewList.txt_total_price
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val item = mValues[position]
        holder.itemName.text = item.name
        holder.itemPrice.text = item.price.toString()
        holder.itemQuantity.setText("0",TextView.BufferType.EDITABLE)

        holder.buttons[0].setOnClickListener { updateItem(item,holder) }
        holder.buttons[1].setOnClickListener { deleteItem(item,holder) }

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
        val itemQuantity : EditText = mView.tf_quantity
        val buttons=arrayOf(mView.btn_add,mView.btn_remove)
    }

    private fun updateTotalPrice()
    {
        txtTotalPrice.text=CartController.getTotalPrice().toString()
    }

    private fun updateItem(item: Product,holder: ViewHolder)
    {
        val quantity=holder.itemQuantity.text.toString().toLongOrNull()
        if(quantity!!>item.stock!!)
        {
            return
        }
        CartController.add(item, quantity)
        updateTotalPrice()
    }

    private fun deleteItem(item: Product,holder: ViewHolder)
    {
        holder.itemQuantity.setText("0",TextView.BufferType.EDITABLE)
        CartController.delete(item)
        updateTotalPrice()
    }
}
