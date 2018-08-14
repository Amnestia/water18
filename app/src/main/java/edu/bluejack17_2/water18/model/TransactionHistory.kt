package edu.bluejack17_2.water18.model

data class TransactionHistory(var id: String, var date: String?,var products: List<Product>)
{
    override fun toString(): String = id
}