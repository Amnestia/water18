package edu.bluejack17_2.water18.firebase.controller

import edu.bluejack17_2.water18.firebase.Firebase
import edu.bluejack17_2.water18.model.Product

object FirebaseTransactionHistoryController
{
    val db = Firebase.getReference("transaction_history")
    fun insertProduct(item: Product)
    {
        val id= db.push().key
        db.child(id!!).setValue(item)
    }

    fun deleteProduct(item: Product)
    {

    }
}