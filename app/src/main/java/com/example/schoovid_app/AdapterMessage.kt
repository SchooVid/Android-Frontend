package com.example.schoovid_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMessage(val msg: String) : RecyclerView.Adapter<AdapterPostMessage>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPostMessage {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)

        return AdapterPostMessage(view)
    }

    override fun onBindViewHolder(holder: AdapterPostMessage, position: Int) {
        return holder.bindView(msg)
    }

    override fun getItemCount(): Int {
        return 1
    }


}

class AdapterPostMessage(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val messageText: TextView = itemView.findViewById(R.id.msg)

    fun bindView(message: String){

        messageText.text = message

    }

}
