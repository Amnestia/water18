package edu.bluejack17_2.water18.controller

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.firebase.controller.FirebaseTransactionController
import edu.bluejack17_2.water18.model.PairX
import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.model.Transaction
import edu.bluejack17_2.water18.storage.Cart
import edu.bluejack17_2.water18.storage.TransactionStorage
import java.util.*

object TransactionController
{
    val transactions: MutableList<Transaction> = Vector()

    val transaction_map: MutableMap<String, Transaction> = HashMap()

    init
    {
    }

    fun addTransaction(transaction : Transaction)
    {
        if(transaction.timestamp.deleted_at.isNullOrEmpty() || !transaction.cancelled)
        {
            transactions.add(transaction)
            transaction_map.put(transaction.id, transaction)
        }
    }

    fun readTransaction(gdl: OnGetDataListener)
    {
        FirebaseTransactionController.db.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                transactions.clear()
                transaction_map.clear()
                gdl.onSuccess(snapshot)
            }

            override fun onCancelled(e: DatabaseError)
            {
                Log.w("DBError",e.toString())
            }
        })
    }



    fun getList() : List<PairX<Product, Long>>
    {
        return TransactionStorage.transaction.products
    }

    fun calculateTotalPrice(transaction: Transaction) : Long
    {
        var price:Long=0
        for(items in transaction.products)
            price+=Cart.getRes(items.first.price as Long,items.second)
        return price
    }

    fun read(transaction: Transaction)
    {
        FirebaseTransactionController.read(transaction)
    }

    fun cancel(transaction: Transaction)
    {
        FirebaseTransactionController.cancel(transaction)
    }

    fun finish(transaction: Transaction)
    {
        FirebaseTransactionController.finish(transaction)
        ProductController.updateOnFinish(transaction)
    }

    fun checkCurrentTransaction():Boolean
    {
        return TransactionStorage.isInitialized() && !TransactionStorage.transaction.cancelled && !TransactionStorage.transaction.finished
    }

}