package edu.bluejack17_2.water18.firebase.listener

import com.google.firebase.database.DataSnapshot
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.controller.TransactionController
import edu.bluejack17_2.water18.model.Transaction

object TransactionGetDataListener: OnGetDataListener
{
    override fun onSuccess(snapshot: DataSnapshot)
    {
        snapshot.children.forEach { TransactionController.addTransaction(it.getValue<Transaction>(Transaction::class.java) as Transaction) }
    }
}