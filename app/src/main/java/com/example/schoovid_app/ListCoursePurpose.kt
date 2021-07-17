package com.example.schoovid_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.callapi.MyDataItem
import com.example.callapi.ServiceGenerator
import com.example.loginapi.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCoursePurpose : AppCompatActivity(), onCourseItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_course_purpose)

        val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
        val call = serviceGenerator.getData()

        val recyclerView = findViewById<RecyclerView>(R.id.listCourse)

        call.enqueue(object : Callback<MutableList<MyDataItem>> {
            override fun onResponse(
                call: Call<MutableList<MyDataItem>>,
                response: Response<MutableList<MyDataItem>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ListCoursePurpose)
                        adapter = PostAdapter(response.body()!!,this@ListCoursePurpose)
                    }
                    Log.e("Success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<MutableList<MyDataItem>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })

        val actionbar = supportActionBar

        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemClick(item: MyDataItem, position: Int) {
        Log.e("TAG", "onItemClick: ", )
    }
}