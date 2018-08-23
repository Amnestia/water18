package edu.bluejack17_2.water18.storage

import android.util.Log
import edu.bluejack17_2.water18.model.Product
import java.util.*

object Cart
{
    var items: MutableList<Product> = Vector()
    var itemMap: MutableMap<Product, Long> = HashMap()

    fun addToCart(item: Product,quantity: Long)
    {
        itemMap[item]=quantity
        if(itemMap[item]!!<1)
            itemMap.remove(item)
    }

    fun add(item: Product,quantity: Long)
    {
        item.stock=quantity
        items.add(item)
    }

    fun addToList()
    {
        items.clear()
        for(item in itemMap)
            add(item.key,item.value)
    }

    fun deleteFromCart(item: Product)
    {
        for(it in itemMap)
        {
            if(it.key.id==item.id)
            {
                itemMap.remove(it.key)
                return
            }
        }
    }

    fun getRes(price: Long, quantity: Long) : Long
    {
        return price*quantity
    }

    fun calculateTotalPrice():Long
    {
        var price:Long=0
        for(item in itemMap)
            price+=getRes(item.key.price as Long,item.value)
        Log.w("total",price.toString())
        return price
    }

    fun clear()
    {
        itemMap.clear()
    }

    fun getList() : List<Product>
    {
        return items
    }

    fun checkInside()
    {
        for(item in itemMap)
            Log.w("items",item.key.name+" "+item.value)
    }

}