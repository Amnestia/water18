package edu.bluejack17_2.water18.adapter.list.ownercpanel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bluejack17_2.water18.R
import edu.bluejack17_2.water18.fragment.ownercpanel.UserControlFragment
import edu.bluejack17_2.water18.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val mValues: List<User>, private val mListener: UserControlFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<UserAdapter.ViewHolder>()
{

    private val mOnClickListener: View.OnClickListener

    init
    {
        mOnClickListener = View.OnClickListener { v ->
            val user = v.tag as User
            mListener?.onListFragmentInteraction(user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val user = mValues[position]
        holder.userName.text = user.name
        holder.userRole.text = user.role.toString()

        with(holder.mView) {
            tag = user
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
    {
        val userName : TextView = mView.txt_user_name
        val userRole : TextView = mView.txt_user_role
    }
}
