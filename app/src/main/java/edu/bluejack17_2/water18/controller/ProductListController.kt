package edu.bluejack17_2.water18.controller

import edu.bluejack17_2.water18.model.Product
import java.util.*

object ProductListController
{

    val items: MutableList<Product> = Vector()

    val item_map: MutableMap<String, Product> = HashMap()

    private fun addItem(item: Product)
    {
        items.add(item)
        item_map.put(item.id, item)
    }

    fun insertToFirebase(item: Product)
    {

    }
}