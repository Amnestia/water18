package edu.bluejack17_2.water18.utility

import java.security.MessageDigest

object Hash
{

    private val salt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"

    fun hashSHA512(pw: String): String?
    {
        val HEX_CHARS="0123456789ABCDEF"
        var str=""
        try
        {
            var toHash=salt+pw+salt
            val md = MessageDigest.getInstance("SHA-512").digest(toHash.toByteArray())
            val sb = StringBuilder(md.size*2)
            md.forEach {
                val i = it.toInt()
                sb.append(HEX_CHARS[i shr 4 and 0x0f])
                sb.append(HEX_CHARS[i and 0x0f])
            }
            str=sb.toString()
        }
        catch(e: Exception)
        {
            e.printStackTrace()
        }

        return str
    }
}