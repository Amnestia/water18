package edu.bluejack17_2.water18.model

data class User(var id: String, var name: String?, var address: String?,
           var phoneNumber: String?, var password: String?,var role: Role?,val timestamp: Timestamp)
{
    override fun toString(): String = id
}
