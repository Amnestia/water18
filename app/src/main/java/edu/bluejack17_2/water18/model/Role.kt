package edu.bluejack17_2.water18.model

data class Role(var id: String, var role: String?,val timestamp: Timestamp)
{
    override fun toString(): String = id
}
