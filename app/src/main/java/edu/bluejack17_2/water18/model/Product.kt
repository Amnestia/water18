package edu.bluejack17_2.water18.model

data class Product(var id: String, var name: String?,
                   var price: Long?,var stock: Long?, var timestamp: Timestamp)
{
    override fun toString(): String = id
    constructor():this("","",0,0,Timestamp())
}