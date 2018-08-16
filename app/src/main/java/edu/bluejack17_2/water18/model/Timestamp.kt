package edu.bluejack17_2.water18.model

import java.util.*

class Timestamp
{
    var created_at: String?
    var updated_at: String?
    var deleted_at: String?
    init
    {
        created_at=Calendar.getInstance().toString()
        updated_at=Calendar.getInstance().toString()
        deleted_at=null
    }
    override fun toString(): String = created_at+" "+updated_at
}
