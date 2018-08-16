package edu.bluejack17_2.water18.firebase.controller

import edu.bluejack17_2.water18.firebase.Firebase
import edu.bluejack17_2.water18.model.TransactionHistory
import java.util.*

object FirebaseTransactionHistoryController
{
    val db = Firebase.getReference("transaction_history")
    fun insertProduct(history: TransactionHistory)
    {
        val id= db.push().key
        db.child(id!!).setValue(history)
    }

    fun deleteProduct(history: TransactionHistory)
    {
        val id=history.id
        db.child(id).child("timestamp").child("deleted_at").setValue(Calendar.getInstance())
    }
}