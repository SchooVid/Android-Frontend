package com.example.schoovid_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loginapi.Request.RegisterCourseRequest
import com.example.loginapi.Retro
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.info_course.*
import kotlinx.android.synthetic.main.list_course.view.*
import kotlinx.android.synthetic.main.signin.*
import kotlinx.android.synthetic.main.signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoCourse : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        val userId = arguments?.getString("userId")
        val courseId = arguments?.getString("courseId")
        val course = arguments?.getString("courseName")
        val firstname = arguments?.getString("firstname")
        val lastname = arguments?.getString("lastname")
        val date = arguments?.getString("date")?.replace("T", " ")?.replace(".000Z", "")
        val desc = arguments?.getString("description")
        val category = arguments?.getString("category")
        val level = arguments?.getString("level")

        initData(view, course.toString(), firstname.toString(), lastname.toString(), date.toString(), desc.toString(), category.toString(), level.toString())

        initAction(courseId.toString(), userId.toString())

        btnBack.setOnClickListener {

            val fragment = ListCourse()
            val args = Bundle()
            args.putString("userId", userId)
            fragment.arguments = args

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container1, fragment)
                .commitAllowingStateLoss()

        }

        btnJoinStream.setOnClickListener {

            val fragment = StreamChat()
            val args = Bundle()
            args.putString("userId", userId)
            args.putString("courseId", courseId)
            args.putString("courseName", course)
            args.putString("firstname",firstname)
            args.putString("lastname", lastname)
            args.putString("date", date)
            args.putString("description", desc)
            args.putString("category", category)
            args.putString("level", level)
            fragment.arguments = args

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container1, fragment)
                .commitAllowingStateLoss()

        }

        val request = RegisterCourseRequest()
        request.participantId = userId
        request.courseId = courseId

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.compareRegister(request).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>){
                if(response.body() == true){
                    btnRegistration.setVisibility(View.INVISIBLE)
                    btnJoinStream.setVisibility(View.VISIBLE)
                }else{
                    btnRegistration.setVisibility(View.VISIBLE)
                    btnJoinStream.setVisibility(View.INVISIBLE)
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("Error", "Message :" + t.message)
            }

        })

    }

    fun initAction(courseId:String,participantId:String){

        btnRegistration.setOnClickListener {
            register(courseId,participantId)
        }

    }

    fun register(courseId: String, participantId: String){

        val request = RegisterCourseRequest()
        request.courseId = courseId
        request.participantId = participantId

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.registerCourse(request).enqueue(object : Callback<RegisterCourseRequest> {
            override fun onResponse(call: Call<RegisterCourseRequest>, response: Response<RegisterCourseRequest>){
                if (response.isSuccessful) {
                    btnRegistration.setVisibility(View.INVISIBLE)
                    btnJoinStream.setVisibility(View.VISIBLE)
                    Toast.makeText(this@InfoCourse.context, "Successful registration", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@InfoCourse.context, "Registration failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterCourseRequest>, t: Throwable) {
                Log.e("Error", "Message :" + t.message)
            }

        })

    }

    fun initData(view: View, course: String, firstname:String, lastname:String, date:String, desc: String, category:String, level:String){

        val courseName:TextView = view.findViewById(R.id.courseName)
        courseName.text = course

        val fullname = firstname + " " + lastname
        Log.e("TAG", fullname)
        val teacherName:TextView = view.findViewById(R.id.teacherName)
        teacherName.text = "Name teacher : " + fullname

        val dateStream:TextView = view.findViewById(R.id.date)
        dateStream.text= "Date : " + date

        val description:TextView = view.findViewById(R.id.description)
        description.text = desc.toString()

        val cat:TextView = view.findViewById(R.id.category)
        cat.text = "Category : " + category

        val lvl:TextView = view.findViewById(R.id.level)
        lvl.text = "Level : " + level

    }
}