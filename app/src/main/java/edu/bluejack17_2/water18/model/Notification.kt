package edu.bluejack17_2.water18.model

class Notification(var id:String,val tag:String,val user:String?, val item: String?, val timestamp: Timestamp)
{
    constructor():this("","",null,null,Timestamp())
}