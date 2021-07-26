package com.example.schoovid_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoovid_app.Request.DataCoursePropose
import com.example.callapi.ServiceGenerator
import com.example.loginapi.UserApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.list_course.view.*
import kotlinx.android.synthetic.main.list_course_propose.*
import kotlinx.android.synthetic.main.list_course_propose.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCoursePropose: Fragment(), onCourseProposeItemClickListener{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_course_propose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
        val call = serviceGenerator.getCoursePropose()

        val recyclerView = view.findViewById<RecyclerView>(R.id.listCoursePropose)

        call.enqueue(object : Callback<MutableList<DataCoursePropose>> {
            override fun onResponse(call: Call<MutableList<DataCoursePropose>>, response: Response<MutableList<DataCoursePropose>>) {
                if (response.isSuccessful) {
                    recyclerView.apply {
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

        var dialog = Pop()
        val args = Bundle()
        args.putString("courseName", dataCoursePropose.libelle)
        args.putString("courseId", dataCoursePropose.id)
        dialog.setArguments(args)
        dialog.show(parentFragmentManager, "Popup")

    }

}