package edu.bluejack17_2.water18.storage

import edu.bluejack17_2.water18.model.Transaction
import java.util.*

object HistoryStorage
{
    var histories: MutableList<Transaction> = Vector()
}