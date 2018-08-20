package edu.bluejack17_2.water18.controller

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.bluejack17_2.water18.firebase.controller.FirebaseProductController
import edu.bluejack17_2.water18.model.Product
import java.util.*

object ProductListController
{
    val items: MutableList<Product> = Vector()

    val item_map: MutableMap<String, Product> = HashMap()

    private fun addItem(item: Product)
    {
        if(item.timestamp.deleted_at.isNullOrEmpty())
        {
            items.add(item)
            item_map.put(item.id, item)
        }
    }

    fun insert(item: Product)
    {
        FirebaseProductController.insertProduct(item)
        read()
    }

    fun read()
    {
        FirebaseProductController.db.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                items.clear()
                item_map.clear()
                snapshot.children.forEach { addItem(it.getValue<Product>(Product::class.java) as Product) }
            }

            override fun onCancelled(e: DatabaseError)
            {
                Log.w("DBError",e.toString())
            }
        })
    }

    fun update(item: Product)
    {
        FirebaseProductController.updateProduct(item)
        read()
    }

    fun delete(item: Product)
    {
        FirebaseProductController.deleteProduct(item)
        read()
    }
}