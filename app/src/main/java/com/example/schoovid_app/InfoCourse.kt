package com.example.schoovid_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.loginapi.Request.RegisterCourseRequest
import com.example.loginapi.Retro
import com.example.loginapi.UserApi
import com.example.schoovid_app.Request.MyDataItem
import kotlinx.android.synthetic.main.info_course.*
import kotlinx.android.synthetic.main.signin.*
import kotlinx.android.synthetic.main.signup.*
import retrofit2.Call
import retrofit2.Response

//class InfoCourse : AppCompatActivity() {
class InfoCourse : Fragment(){

    /*companion object {
        fun newInstance(myDataItem: MyDataItem) : InfoCourse {
            val fragment = InfoCourse()
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
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //(activity as AppCompatActivity).supportActionBar?.title = "List of course"
        (activity as AppCompatActivity).supportActionBar?.hide()

        val userId = arguments?.getString("userId")
        Log.e("TAG456", userId.toString())

        val courseId = arguments?.getString("courseId")
        Log.e("TAG456", courseId.toString())

        val course = arguments?.getString("courseName")
        val courseName:TextView = view.findViewById(R.id.courseName)
        courseName.text = course.toString()

        val firstname = arguments?.getString("firstname")
        val lastname = arguments?.getString("lastname")
        val fullname = "$firstname $lastname"
        Log.e("TAG", fullname)
        val teacherName:TextView = view.findViewById(R.id.teacherName)
        teacherName.text = fullname

        val date = arguments?.getString("date")?.replace("T", " ")?.replace(".000Z", "")
        val dateStream:TextView = view.findViewById(R.id.date)
        dateStream.text = date.toString()

        val desc = arguments?.getString("description")
        val description:TextView = view.findViewById(R.id.description)
        description.text = desc.toString()

        initAction(courseId.toString(), userId.toString())

        btnBack.setOnClickListener {

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container1, ListCourse())
                .commitAllowingStateLoss()

        }

    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val title = arguments?.getString("nameCourse")
        Log.e("TAG", title.toString())*/

    }*/

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_course)

        val courseId = intent.getStringExtra("Id")
        val participantId = intent.getStringExtra("userId")

        courseName.text = intent.getStringExtra("CourseName")
        teacherName.text = "Name teacher : " +intent.getStringExtra("FirstnameTeacher") + " " + intent.getStringExtra("LastnameTeacher")
        description.text = intent.getStringExtra("Description")
        val newDate = intent.getStringExtra("Date")?.replace("T", " ")
        date.text = "Date : " + newDate?.replace(".000Z", "")

        //Log.e("TAG", courseId.toString())
        //Log.e("TAG", userId.toString())

        val actionbar = supportActionBar

        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        if (participantId != null) {
            if (courseId != null) {
                initAction(courseId, participantId)
            }
        }

        btnJoinStream.setOnClickListener {
            val intent = Intent(this, StreamChat::class.java)
            startActivity(intent)
        }

    }*/

    /*override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }*/

    fun onBackPressed(): Boolean{

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container1, ListCourse())
            .commitAllowingStateLoss()

        return true
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
        retro.registerCourse(request).enqueue(object : retrofit2.Callback<RegisterCourseRequest> {
            override fun onResponse(call: Call<RegisterCourseRequest>, response: Response<RegisterCourseRequest>){
                val user = response.body()
                if (response.isSuccessful) {
                    Log.e("TAG", "test")
                    btnRegistration.setVisibility(View.INVISIBLE)
                    /*val intent = Intent(this@InfoCourse, Signin::class.java)
                    startActivity(intent)*/
                }else{
                    Log.e("Erreur", "Not register")
                }
            }

            override fun onFailure(call: Call<RegisterCourseRequest>, t: Throwable) {
                Log.e("Error", "Message :" + t.message)
            }

        })

    }
}