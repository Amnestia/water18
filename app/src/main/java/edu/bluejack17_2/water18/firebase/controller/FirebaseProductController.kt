package edu.bluejack17_2.water18.firebase.controller

import edu.bluejack17_2.water18.firebase.Firebase
import edu.bluejack17_2.water18.model.Product
import java.util.*

object FirebaseProductController
{
    val db = Firebase.getReference("product")
    fun insertProduct(item: Product)
    {
        val id= db.push().key
        db.child(id!!).setValue(item)
    }

    fun deleteProduct(item: Product)
    {
        val id=item.id
        db.child(id!!).child("timestamp").child("deleted_at").setValue(Calendar.getInstance())
    }
}