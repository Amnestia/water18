package edu.bluejack17_2.water18.model

data class Timestamp(val id: String, val createdAt: String?,val updatedAt: String?,val deletedAt: String?)
{
    override fun toString(): String = id
}