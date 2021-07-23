package com.example.schoovid_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoovid_app.Request.MyDataItem
import com.example.callapi.ServiceGenerator
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.list_course.*
import kotlinx.android.synthetic.main.list_course.view.*
import kotlinx.android.synthetic.main.list_course_propose.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*class ListCourse : AppCompatActivity(), onCourseItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_course)

        val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
        val call = serviceGenerator.getCourse()

        val recyclerView = findViewById<RecyclerView>(R.id.listCourse)

        call.enqueue(object : Callback<MutableList<MyDataItem>> {
            override fun onResponse(
                call: Call<MutableList<MyDataItem>>,
                response: Response<MutableList<MyDataItem>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ListCourse)
                        adapter = PostAdapter(response.body()!!,this@ListCourse)
                    }
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

    override fun onItemClick(myDataItem: MyDataItem, position: Int) {
        val userId = intent.getStringExtra("userId")
        val intent = Intent(this, InfoCourse::class.java)
        intent.putExtra("Id", myDataItem.id)
        intent.putExtra("userId", userId)
        intent.putExtra("CourseName", myDataItem.libelle)
        intent.putExtra("FirstnameTeacher", myDataItem.firstnameTeacher)
        intent.putExtra("LastnameTeacher", myDataItem.lastnameTeacher)
        intent.putExtra("Description", myDataItem.description)
        intent.putExtra("Date", myDataItem.dateDiffusion)
        startActivity(intent)
    }
}*/

class ListCourse : Fragment(), onCourseItemClickListener{

    companion object {
        fun newInstance(myDataItem: MyDataItem) : ListCourse {
            val fragment = ListCourse()
            val args = Bundle()
            args.putString("courseId", myDataItem.id)
            args.putString("nameCourse", myDataItem.libelle)
            args.putString("firstname", myDataItem.firstnameTeacher)
            args.putString("lastname", myDataItem.lastnameTeacher)
            args.putString("date", myDataItem.dateDiffusion)
            args.putString("description", myDataItem.description)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.show()

        val userId = arguments?.getString("userId")
        Log.e("TAG123", userId.toString())

        val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
        val call = serviceGenerator.getCourse()

        val recyclerView = view.findViewById<RecyclerView>(R.id.listCourse)

        call.enqueue(object : Callback<MutableList<MyDataItem>> {
            override fun onResponse(call: Call<MutableList<MyDataItem>>, response: Response<MutableList<MyDataItem>>) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        //layoutManager = LinearLayoutManager(this)
                        view.listCourse.layoutManager = LinearLayoutManager(requireContext())
                        adapter = PostAdapter(response.body()!!,this@ListCourse)

                    }
                }
            }

            override fun onFailure(call: Call<MutableList<MyDataItem>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })

        btnReload.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container1, ListCourse())
                .commitAllowingStateLoss()
        }

    }

    override fun onItemClick(myDataItem: MyDataItem, position: Int) {

        val userId = arguments?.getString("userId")

        val fragment = InfoCourse()
        val args = Bundle()
        args.putString("userId", userId)
        args.putString("courseId", myDataItem.id)
        args.putString("courseName", myDataItem.libelle)
        args.putString("firstname", myDataItem.firstnameTeacher)
        args.putString("lastname", myDataItem.lastnameTeacher)
        args.putString("date", myDataItem.dateDiffusion)
        args.putString("description", myDataItem.description)
        fragment.arguments = args

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container1, fragment)
            .commitAllowingStateLoss()
    }


}