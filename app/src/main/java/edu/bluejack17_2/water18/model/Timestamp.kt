package edu.bluejack17_2.water18.model

import java.util.*

class Timestamp
{
    var created_at: String?
    var updated_at: String?
    var deleted_at: String?
    init
    {
        created_at=Date().toString()
        updated_at=Date().toString()
        deleted_at=""
    }
    override fun toString(): String = created_at+" "+updated_at
}
