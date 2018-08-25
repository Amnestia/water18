package edu.bluejack17_2.water18.firebase.listener

import com.google.firebase.database.DataSnapshot
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.controller.HistoryController
import edu.bluejack17_2.water18.model.Transaction

object HistoryGetDataListener : OnGetDataListener
{
    override fun onSuccess(snapshot: DataSnapshot)
    {
        snapshot.children.forEach { HistoryController.addHistory(it.getValue<Transaction>(Transaction::class.java) as Transaction) }
    }
}