package edu.bluejack17_2.water18.adapter.list.customer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.controller.CartController
import edu.bluejack17_2.water18.fragment.customer.CartFragment
import edu.bluejack17_2.water18.model.PairX
import edu.bluejack17_2.water18.model.Product
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(private val mValues: List<PairX<Product, Long>>, private val mListener: CartFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<CartAdapter.ViewHolder>()
{

    private val mOnClickListener: View.OnClickListener

    private lateinit var txtTotalPrice: TextView
    private lateinit var list : RecyclerView
    init
    {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Product
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        val viewList = LayoutInflater.from(parent.context).inflate(R.layout.fragment_cart, parent, false)
        txtTotalPrice=viewList.txt_total_price
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val item = mValues[position]
        holder.itemName.text = item.first.name
        holder.itemPrice.text = item.first.price.toString()
        holder.itemQuantity.text=item.second.toString()

        holder.btnRemove.setOnClickListener { deleteItem(item.first,holder) }

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
        val btnRemove=mView.btn_remove
    }

    private fun refreshView(txt:TextView)
    {
        txt.visibility=TextView.GONE
        txt.visibility=TextView.VISIBLE
    }

    private fun updateTotalPrice()
    {
        txtTotalPrice.setText(CartController.getTotalPrice().toString(),TextView.BufferType.NORMAL)
        refreshView(txtTotalPrice)
    }

    private fun deleteItem(item: Product, holder: ViewHolder)
    {
        CartController.delete(item)
        updateTotalPrice()
    }
}