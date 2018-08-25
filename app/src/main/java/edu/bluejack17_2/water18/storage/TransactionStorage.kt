package edu.bluejack17_2.water18.storage

import edu.bluejack17_2.water18.model.Transaction

object TransactionStorage
{
    lateinit var transaction: Transaction

    fun isInitialized():Boolean
    {
        return this::transaction.isInitialized
    }
}