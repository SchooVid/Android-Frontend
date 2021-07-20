package com.example.schoovid_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.schoovid_app.Request.DataCoursePropose

class PostAdapterCoursePropose(val dataCoursePropose: MutableList<DataCoursePropose>, var clickListener: onCourseProposeItemClickListener): RecyclerView.Adapter<AdapterCoursePropose>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCoursePropose {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)

        return AdapterCoursePropose(view)
    }

    override fun onBindViewHolder(holder: AdapterCoursePropose, position: Int) {
        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,dataCoursePropose[position].libelle, Toast.LENGTH_SHORT).show()
        }
        holder.initialize(dataCoursePropose.get(position), clickListener)
        return holder.bindView(dataCoursePropose[position])
    }

    override fun getItemCount(): Int {
        return dataCoursePropose.size
    }

}

class AdapterCoursePropose(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val courseName: TextView = itemView.findViewById(R.id.courseName)

    fun bindView(dataCoursePropose: DataCoursePropose){

        courseName.text = dataCoursePropose.libelle

    }

    fun initialize(item: DataCoursePropose, action: onCourseProposeItemClickListener){
        itemView.setOnClickListener {
            action.onItemClick(item, adapterPosition)
        }
    }

}

interface onCourseProposeItemClickListener{
    fun onItemClick(dataCoursePropose: DataCoursePropose, position: Int)
}