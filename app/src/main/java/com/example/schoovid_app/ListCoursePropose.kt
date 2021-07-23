package com.example.schoovid_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoovid_app.Request.DataCoursePropose
import com.example.callapi.ServiceGenerator
import com.example.loginapi.UserApi
import com.example.schoovid_app.Fragment.CourseFragment
import com.example.schoovid_app.Request.MyDataItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.list_course.view.*
import kotlinx.android.synthetic.main.list_course_propose.*
import kotlinx.android.synthetic.main.list_course_propose.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*class ListCoursePropose: AppCompatActivity(), onCourseProposeItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_course_propose)

        val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
        val call = serviceGenerator.getCoursePropose()

        val recyclerView = findViewById<RecyclerView>(R.id.listCoursePropose)

        call.enqueue(object : Callback<MutableList<DataCoursePropose>> {
            override fun onResponse(
                call: Call<MutableList<DataCoursePropose>>,
                response: Response<MutableList<DataCoursePropose>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ListCoursePropose)
                        adapter = PostAdapterCoursePropose(response.body()!!,this@ListCoursePropose)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<DataCoursePropose>>, t: Throwable) {
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

    override fun onItemClick(dataCoursePropose: DataCoursePropose, position: Int) {
        val intent = Intent(this, Pop::class.java)
        intent.putExtra("nameCourse", dataCoursePropose.libelle)
        intent.putExtra("courseId", dataCoursePropose.id)
        startActivity(intent)
    }

    fun clickCreateCourse(view: View) {
        btnAddCourse.setOnClickListener {
            val userId = intent.getStringExtra("userId")
            val intent = Intent(this, AddCoursePropose::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }

}*/

class ListCoursePropose: Fragment(), onCourseProposeItemClickListener{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_course_propose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getString("userId")
        Log.e("TAG123", userId.toString())

        val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
        val call = serviceGenerator.getCoursePropose()

        val recyclerView = view.findViewById<RecyclerView>(R.id.listCoursePropose)

        call.enqueue(object : Callback<MutableList<DataCoursePropose>> {
            override fun onResponse(call: Call<MutableList<DataCoursePropose>>, response: Response<MutableList<DataCoursePropose>>) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        //layoutManager = LinearLayoutManager(this)
                        view.listCoursePropose.layoutManager = LinearLayoutManager(requireContext())
                        adapter = PostAdapterCoursePropose(response.body()!!,this@ListCoursePropose)

                    }
                }
            }

            override fun onFailure(call: Call<MutableList<DataCoursePropose>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })

        val btn = view.findViewById<FloatingActionButton>(R.id.btnAddCourse)

        btn.setOnClickListener {
            val fragment = AddCoursePropose()
            val userId = arguments?.getString("userId")
            val args = Bundle()
            args.putString("userId", userId)
            fragment.arguments = args

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.containerCoursePropose, fragment)
                .commitAllowingStateLoss()
        }

    }

    override fun onItemClick(dataCoursePropose: DataCoursePropose, position: Int) {

    }

}