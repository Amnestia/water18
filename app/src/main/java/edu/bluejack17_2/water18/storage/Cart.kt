package edu.bluejack17_2.water18.storage

import edu.bluejack17_2.water18.model.PairX
import edu.bluejack17_2.water18.model.Product
import java.util.*

object Cart
{
    var items: MutableList<PairX<Product,Long>> = Vector()

    fun getIndex(item: Product):Int
    {
        for(i in items.indices)
            if(item.id.equals(items[i].first.id))
                return i
        return -1
    }

    fun addToCart(item: Product,quantity: Long)
    {
        val idx= getIndex(item)
        if(idx>-1)
            items[idx].second=quantity
        else
            items.add(PairX(item,quantity))
    }

    fun deleteFromCart(item: Product)
    {
        val idx= getIndex(item)
        if(idx>-1)
        items.removeAt(idx)
    }

    fun getQuantity(item: Product) : Long
    {
        val idx= getIndex(item)
        if(idx>-1)
        return items[idx].second
        return 0
    }

    fun getRes(price: Long, quantity: Long) : Long
    {
        return price*quantity
    }

    fun calculateTotalPrice():Long
    {
        var price:Long=0
        for(item in items)
            price+=getRes(item.first.price as Long,item.second)
        return price
    }

    fun clear()
    {
        items.clear()
    }

    fun getList() : List<PairX<Product,Long>>
    {
        return items
    }
}

