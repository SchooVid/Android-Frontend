package com.example.schoovid_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.socket.client.Socket
import kotlinx.android.synthetic.main.chat.*
import kotlinx.android.synthetic.main.chat.view.*
import kotlinx.android.synthetic.main.list_course.view.*
import kotlinx.android.synthetic.main.list_course.view.listCourse
import org.json.JSONObject

class Chat : Fragment() {

    lateinit var mSocket: Socket

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val app: SocketInstance = this.activity?.application as SocketInstance
        //val app = SocketInstance(this.application)
        mSocket = app.getMSocket()
        return inflater.inflate(R.layout.chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSocket.on(Socket.EVENT_CONNECT) {
            println("Socket connected")
        }
        mSocket.connect()

        val courseId = arguments?.getString("courseId")
        val userId = arguments?.getString("userId")
        val listMessage = view.findViewById<RecyclerView>(R.id.listMessage)

        mSocket.emit("join_room", courseId)

        mSocket.on("comment"){
                values -> Log.e("TAG", values[0].toString())
            val jsonOBJ = JSONObject(values[0].toString())
            Log.e("MESSAGE", jsonOBJ.getString("text"))
            listMessage.apply {
                view.listMessage.layoutManager = LinearLayoutManager(requireContext())
                adapter = AdapterMessage(jsonOBJ.getString("text"))
            }
        }



        sendBtn.setOnClickListener {

            val message = messageEdit.text.toString().trim()

            val data = JSONObject()
            data.put("roomKey", courseId)
            data.put("user", "David")
            data.put("text", message)

            mSocket.emit("comment", data)
            Log.e("TAG", messageEdit.text.toString().trim())

        }

    }

}