package com.musicplayer.musicManagement.mainPlaylist.services

object MemoryCache{
    private val data: LinkedHashMap<String, Any> = LinkedHashMap()

    fun put(key:String, value:Any){
        data[key] = value
    }
    fun exits(key: String):Boolean{
        return data[key] != null
    }
    fun get(key:String):Any?{
        return data[key]
    }

    fun clear(key: String) {
        data.remove(key)
    }
}