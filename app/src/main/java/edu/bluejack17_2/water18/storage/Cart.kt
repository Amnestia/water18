package edu.bluejack17_2.water18.storage

import edu.bluejack17_2.water18.model.Product

object Cart
{
    var itemMap: MutableMap<Product, Long> = HashMap()

    fun addToCart(item: Product,quantity: Long)
    {
        itemMap[item]=quantity
        if(itemMap[item]!!<1)
            itemMap.remove(item)
    }

    fun deleteToCart(item: Product)
    {
        itemMap.remove(item)
    }

    fun getRes(price: Long, quantity: Long) : Long
    {
        return price*quantity
    }

    fun calculateTotalPrice():Long
    {
        var price:Long=0
        itemMap.forEach{ product, quantity -> price.plus(getRes(product.price as Long,quantity)) }
        return price
    }

    fun clear()
    {
        itemMap.clear()
    }
}