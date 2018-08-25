package edu.bluejack17_2.water18.firebase.controller

import edu.bluejack17_2.water18.firebase.Firebase
import edu.bluejack17_2.water18.model.Transaction
import edu.bluejack17_2.water18.storage.TransactionStorage
import java.util.*

object FirebaseTransactionController
{
    val db = Firebase.getReference("transaction")

    fun insert(transaction: Transaction)
    {
        val id= db.push().key
        transaction.id=id as String
        db.child(id).setValue(transaction)
        TransactionStorage.transaction=transaction
    }

    fun update(transaction: Transaction)
    {
        transaction.timestamp.updated_at=Date().toString()
    }

    fun read(transaction: Transaction)
    {
        val id=transaction.id
        transaction.read=true
        update(transaction)
        db.child(id).setValue(transaction)
    }

    fun cancel(transaction: Transaction)
    {
        val id=transaction.id
        transaction.cancelled=true
        transaction.timestamp.deleted_at=Date().toString()
        db.child(id).setValue(transaction)
    }

    fun finish(transaction: Transaction)
    {
        val id=transaction.id
        transaction.finished=true
        update(transaction)
        db.child(id).setValue(transaction)
    }
}