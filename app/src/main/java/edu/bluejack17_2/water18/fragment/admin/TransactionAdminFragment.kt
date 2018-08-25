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
import edu.bluejack17_2.water18.activity.ChatActivity
import edu.bluejack17_2.water18.adapter.list.admin.TransactionProductAdapter
import edu.bluejack17_2.water18.controller.TransactionController
import edu.bluejack17_2.water18.fragment.HomeFragment
import edu.bluejack17_2.water18.model.Transaction
import edu.bluejack17_2.water18.notification.Notifier
import edu.bluejack17_2.water18.storage.TransactionStorage
import kotlinx.android.synthetic.main.fragment_transaction_admin_view.*
import kotlinx.android.synthetic.main.fragment_transaction_admin_view.view.*
import org.jetbrains.anko.support.v4.intentFor

class TransactionAdminFragment : Fragment(), View.OnClickListener
{
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    private fun addListener() = arrayOf(btn_mark_as_read,btn_chat,btn_finish_order).forEach { it.setOnClickListener(this) }

    companion object
    {
        fun newInstance() = TransactionAdminFragment()
    }

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

    fun initView(view:View)
    {
        view.txt_address.text=TransactionStorage.transaction.user.address
        view.txt_phone_number.text=TransactionStorage.transaction.user.phoneNumber
        if(TransactionStorage.transaction.finished)
        {
            view.btn_mark_as_read.isEnabled=false
            view.btn_finish_order.isEnabled=false
            view.btn_chat.isEnabled=false
        }
        else if(TransactionStorage.transaction.read)
        {
            view.btn_mark_as_read.isEnabled=false
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_transaction_admin_view, container, false)
        initView(view)
        if(view.list is RecyclerView)
        {

            with(view.list) {
                layoutManager = when
                {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = TransactionProductAdapter(TransactionController.getList(),listener)
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

    override fun onClick(src: View?)
    {
        when(src)
        {
            btn_chat->chat()
            btn_mark_as_read->read()
            btn_finish_order->finish()
            else -> return
        }
    }

    private fun read()
    {
        TransactionController.read(TransactionStorage.transaction)
        Notifier.notifyCustomer(TransactionStorage.transaction.user.id,activity!!.applicationContext,"Read","")
    }

    private fun chat()
    {
        startActivity(intentFor<ChatActivity>())
    }

    private fun finish()
    {
        TransactionController.finish(TransactionStorage.transaction)
        moveFragment(HomeFragment.newInstance())
    }

    private fun moveFragment(fragment: Fragment)
    {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.content_frame,fragment)
            commit()
        }
    }

    interface OnListFragmentInteractionListener
    {
        fun onListFragmentInteraction(transaction: Transaction)
    }
}

