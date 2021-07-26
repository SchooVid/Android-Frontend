package com.example.schoovid_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.callapi.ServiceGenerator
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.popup_window.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Pop : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(R.layout.popup_window, container,false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val courseName = args!!.getString("courseName")
        val courseId = args!!.getString("courseId")

        nameCourse.text = "You want to delete " + courseName + " ?"

        btnDelete.setOnClickListener {
            val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
            val call = serviceGenerator.deleteCoursePropose(courseId!!)

            call.enqueue(object: Callback<Unit>{

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("Error", t.message.toString())
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {

                    if(response.isSuccessful){
                        Toast.makeText(view.context, "Course is delete", Toast.LENGTH_SHORT).show()
                        dismiss()
                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.containerCoursePropose, ListCoursePropose())
                            .commitAllowingStateLoss()
                    }else{
                        Toast.makeText(view.context, "Course not delete", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}
