package com.example.schoovid_app

import android.app.Application
import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

private const val URL = "http://193.70.36.58:3000"

class SocketInstance : Application() {

    private lateinit var mSocket: Socket

    override fun onCreate() {
        super.onCreate()

        try{
            val options = IO.Options.builder().build()
            mSocket = IO.socket(URL, options)
            Log.e("TAG123", "Success")
        }catch (e: URISyntaxException){
            Log.e("TAG456", e.message.toString())
            throw RuntimeException(e)
        }
    }

    fun getMSocket(): Socket {
        return mSocket
    }

}