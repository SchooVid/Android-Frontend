package com.example.schoovid_app

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.callapi.ServiceGenerator
import com.example.loginapi.Request.DataCreateCoursePropose
import com.example.loginapi.Request.SignInRequest
import com.example.loginapi.Retro
import com.example.loginapi.UserApi
import com.example.schoovid_app.Fragment.CourseFragment
import com.example.schoovid_app.Request.DataCoursePropose
import kotlinx.android.synthetic.main.add_course_propose.*
import kotlinx.android.synthetic.main.list_course_propose.view.*
import kotlinx.android.synthetic.main.signin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*class AddCoursePropose : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
        setContentView(R.layout.add_course_propose)

        initAction()

        val actionbar = supportActionBar

        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun initAction(){

        createCourse.setOnClickListener {
            createCourse()
        }

    }

    fun createCourse(){

        val request = DataCreateCoursePropose()
        request.libelle = nameCourse.text.toString().trim()
        request.description = description.text.toString().trim()
        request.userId = intent.getStringExtra("userId")

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.createCoursePropose(request).enqueue(object : Callback<DataCreateCoursePropose> {
            override fun onResponse(call: Call<DataCreateCoursePropose>, response: Response<DataCreateCoursePropose>){
                val user = response.body()
                if (response.isSuccessful) {
                    val intent = Intent(this@AddCoursePropose, ListCoursePropose::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext, "Course don't create", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DataCreateCoursePropose>, t: Throwable) {
                Log.e("Error", "Message :" + t.message)
            }

        })

    }

}*/

class AddCoursePropose : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_course_propose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAction()

    }

    fun initAction(){

        createCourse.setOnClickListener {
            createCourse()
        }

    }

    fun createCourse(){

        val request = DataCreateCoursePropose()
        request.libelle = nameCourse.text.toString().trim()
        request.description = description.text.toString().trim()
        val userId = arguments?.getString("userId")
        Log.e("UserId", userId.toString())
        request.userId = userId

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.createCoursePropose(request).enqueue(object : Callback<DataCreateCoursePropose> {
            override fun onResponse(call: Call<DataCreateCoursePropose>, response: Response<DataCreateCoursePropose>){
                val user = response.body()
               /* if (response.isSuccessful) {
                    Log.e("Good", "Cours ajouté")
                }else{
                    Log.e("False", "Cours non ajouté")
                }*/
                Log.e("Code", response.code().toString())
            }

            override fun onFailure(call: Call<DataCreateCoursePropose>, t: Throwable) {
                Log.e("Error", "Message :" + t.message)
            }

        })

       

    }

}