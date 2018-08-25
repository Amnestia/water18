package edu.bluejack17_2.water18.storage

import edu.bluejack17_2.water18.utility.random
import java.util.*

object NotificationIDStorage
{
    val ids: MutableList<Int> = Vector()

    fun existed(id:Int) : Int
    {
        for(i in ids.indices)
            if(id==ids[i])
                return i
        return -1
    }

    fun generateID() : Int
    {
        var x=(0..(Int.MAX_VALUE-1)).random()
        while(existed(x)>-1)
        {
            x=(0..(Int.MAX_VALUE-1)).random()
        }
        ids.add(x)
        return x
    }

    fun remove(id:Int)
    {
        ids.remove(existed(id))
    }
}