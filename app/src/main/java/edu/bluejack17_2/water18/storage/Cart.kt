package edu.bluejack17_2.water18.storage

import edu.bluejack17_2.water18.model.Product

object Cart
{
    lateinit var itemMap: MutableMap<Product, Int>

    fun addToCart(item: Product,quantity: Int)
    {
        if(itemMap==null)
            itemMap = HashMap()
        itemMap[item]=quantity
    }
}