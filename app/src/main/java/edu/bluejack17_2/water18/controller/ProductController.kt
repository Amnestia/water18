package edu.bluejack17_2.water18.controller

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.firebase.controller.FirebaseProductController
import edu.bluejack17_2.water18.model.Product
import edu.bluejack17_2.water18.model.Transaction
import edu.bluejack17_2.water18.storage.UserStorage
import java.util.*

object ProductController
{
    val items: MutableList<Product> = Vector()

    val item_map: MutableMap<String, Product> = HashMap()

    fun addItem(item: Product)
    {
        if(item.timestamp.deleted_at.isNullOrEmpty() || (UserStorage.user!=null && UserStorage.user?.role.equals("customer") && item.stock!!>0))
        {
            items.add(item)
            item_map.put(item.id, item)
        }
    }

    fun insert(item: Product)
    {
        FirebaseProductController.insertProduct(item)
    }

    fun read(gdl: OnGetDataListener)
    {
        FirebaseProductController.db.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                items.clear()
                item_map.clear()
                gdl.onSuccess(snapshot)
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
    }

    fun delete(item: Product)
    {
        FirebaseProductController.deleteProduct(item)
    }

    fun updateOnFinish(transaction: Transaction)
    {
        for(item in transaction.products)
        {
            item.first.stock= item.first.stock?.minus(item.second)
            update(item.first)
        }
    }
}