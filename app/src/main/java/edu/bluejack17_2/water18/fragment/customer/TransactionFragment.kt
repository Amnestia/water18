package edu.bluejack17_2.water18.fragment.customer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.activity.ChatActivity
import edu.bluejack17_2.water18.activity.TrackerActivity
import edu.bluejack17_2.water18.controller.TransactionController
import edu.bluejack17_2.water18.fragment.customer.tab.view.HomeHistoryParentFragment
import edu.bluejack17_2.water18.storage.TransactionStorage
import kotlinx.android.synthetic.main.fragment_transaction.*
import org.jetbrains.anko.support.v4.intentFor

class TransactionFragment : Fragment(), View.OnClickListener
{
    companion object
    {
        fun newInstance() = TransactionFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let{

        }
    }

    fun addListener()
    {
        arrayOf(btn_cancel,btn_chat,btn_track).forEach { it.setOnClickListener(this) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        addListener()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onClick(src: View?)
    {
        when(src)
        {
            btn_cancel -> cancel()
            btn_chat -> chat()
            btn_track -> track()
            else -> return
        }
    }

    fun cancel()
    {
        TransactionController.cancel(TransactionStorage.transaction)

        moveFragment(HomeHistoryParentFragment.newInstance())
    }

    fun chat()
    {
        startActivity(intentFor<ChatActivity>())
    }

    fun track()
    {
        startActivity(intentFor<TrackerActivity>())
    }

    fun moveFragment(fragment: Fragment)
    {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.content_frame,fragment)
            commit()
        }
    }
}