package edu.bluejack17_2.water18.firebase.listener

import com.google.firebase.database.DataSnapshot
import edu.bluejack17_2.water18.baseclass.OnGetDataListener
import edu.bluejack17_2.water18.controller.ProductController
import edu.bluejack17_2.water18.model.Product

object ProductGetDataListener : OnGetDataListener
{
    override fun onSuccess(snapshot: DataSnapshot)
    {
        snapshot.children.forEach { ProductController.addItem(it.getValue<Product>(Product::class.java) as Product) }
    }
}