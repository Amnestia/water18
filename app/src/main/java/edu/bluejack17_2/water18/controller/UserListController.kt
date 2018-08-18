package edu.bluejack17_2.water18.controller

import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.model.User
import java.util.*

object UserListController
{

    val items: MutableList<User> = Vector()

    val item_map: MutableMap<String, User> = HashMap()

    private fun addItem(item: User)
    {
        items.add(item)
        item_map.put(item.id, item)
    }

    fun insertToFirebase(item: Product)
    {

    }
}