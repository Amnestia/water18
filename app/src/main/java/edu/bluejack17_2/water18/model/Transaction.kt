package edu.bluejack17_2.water18.model

import java.util.*

data class Transaction(var id: String, var user: User, var date: String?,
                       var products: List<PairX<Product,Long>>,
                       var cancelled: Boolean, var read: Boolean, var finished: Boolean,
                       var timestamp: Timestamp)
{
    override fun toString(): String = id
    constructor():this("",User(),"", Vector(),false,false,false,Timestamp())
}