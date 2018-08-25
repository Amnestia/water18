package edu.bluejack17_2.water18.controller

import edu.bluejack17_2.water18.firebase.controller.FirebaseTransactionController
import edu.bluejack17_2.water18.model.PairX
import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.model.Timestamp
import edu.bluejack17_2.water18.model.Transaction
import edu.bluejack17_2.water18.storage.Cart
import edu.bluejack17_2.water18.storage.TransactionStorage
import edu.bluejack17_2.water18.storage.UserStorage
import java.util.*

object CartController
{
    fun add(item: Product, quantity: Long)
    {
        Cart.addToCart(item,quantity)
    }

    fun delete(item: Product)
    {
        Cart.deleteFromCart(item)
    }

    fun getTotalPrice(): Long
    {
        return Cart.calculateTotalPrice()
    }

    fun getCurrentQuantity(item: Product) : Long
    {
        return Cart.getQuantity(item)
    }

    fun removeAll()
    {
        Cart.clear()
    }

    fun getList(): List<PairX<Product, Long>>
    {
        return Cart.getList()
    }

    fun checkout()
    {
        val transaction=Transaction("", UserStorage.user!!, Date().toString(), getList(),false,false, false,Timestamp())
        TransactionStorage.transaction=transaction
        FirebaseTransactionController.insert(transaction)
        removeAll()
    }
}