package edu.bluejack17_2.water18.controller

import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.storage.Cart

object CartController
{
    fun add(item: Product, quantity: Long)
    {
        Cart.addToCart(item,quantity)
    }

    fun delete(item: Product)
    {
        Cart.deleteToCart(item)
    }

    fun getTotalPrice(): Long
    {
        return Cart.calculateTotalPrice()
    }

    fun removeAll()
    {
        Cart.clear()
    }
}