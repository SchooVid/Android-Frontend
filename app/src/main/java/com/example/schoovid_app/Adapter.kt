package com.example.schoovid_app

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.callapi.MyDataItem

class PostAdapter(val myDataItem: MutableList<MyDataItem>, var clickListener: onCourseItemClickListener): RecyclerView.Adapter<Adapter>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)

        return Adapter(view)
    }

    override fun onBindViewHolder(holder: Adapter, position: Int) {
        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,myDataItem[position].libelle, Toast.LENGTH_SHORT).show()
        }
        holder.initialize(myDataItem.get(position), clickListener)
        return holder.bindView(myDataItem[position])
    }

    override fun getItemCount(): Int {
        return myDataItem.size
    }

}

class Adapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val courseName: TextView = itemView.findViewById(R.id.courseName)
    private val teacherName: TextView = itemView.findViewById(R.id.teacherName)
    private val dateCourse: TextView = itemView.findViewById(R.id.dateCourse)

    fun bindView(myDataItem: MyDataItem){

        courseName.text = myDataItem.libelle
        teacherName.text = myDataItem.description
        dateCourse.text = myDataItem.dateDiffusion

    }

    fun initialize(item: MyDataItem, action: onCourseItemClickListener){
        itemView.setOnClickListener {
            action.onItemClick(item, adapterPosition)
        }
    }
}

interface onCourseItemClickListener{
    fun onItemClick(myDataItem: MyDataItem, position: Int)
}