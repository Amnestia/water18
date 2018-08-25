package edu.bluejack17_2.water18.controller

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.firebase.controller.FirebaseTransactionController
import edu.bluejack17_2.water18.model.Transaction
import edu.bluejack17_2.water18.storage.HistoryStorage
import edu.bluejack17_2.water18.storage.UserStorage

object HistoryController
{
    fun addHistory(transaction : Transaction)
    {
        if(transaction.timestamp.deleted_at.isNullOrEmpty() || transaction.finished
          || transaction.user.id.equals(UserStorage.user?.id))
        {
            HistoryStorage.histories.add(transaction)
        }
    }

    fun readHistory(gdl : OnGetDataListener)
    {
        FirebaseTransactionController.db.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                TransactionController.transactions.clear()
                TransactionController.transaction_map.clear()
                gdl.onSuccess(snapshot)
            }

            override fun onCancelled(e: DatabaseError)
            {
                Log.w("DBError",e.toString())
            }
        })
    }
}