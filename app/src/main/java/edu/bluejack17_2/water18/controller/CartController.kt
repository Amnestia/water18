package edu.bluejack17_2.water18.controller

import android.util.Log
import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.storage.Cart

object CartController
{
    fun checkInside()
    {
        Cart.checkInside()
    }

    fun add(item: Product, quantity: Long)
    {
        Cart.addToCart(item,quantity)
        checkInside()
        Log.w("asd","add")
    }

    fun delete(item: Product)
    {
        Cart.deleteFromCart(item)
        checkInside()
        Log.w("asd","delete")
    }

    fun getTotalPrice(): Long
    {
        Log.w("asd","total")
        return Cart.calculateTotalPrice()
    }

    fun removeAll()
    {
        Cart.clear()
    }

    fun getList(): List<Product>
    {
        Cart.addToList()
        return Cart.getList()
    }
}