package com.example.schoovid_app

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
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
import com.example.schoovid_app.Request.DataLevel
import com.example.schoovid_app.Request.MyDataItem
import kotlinx.android.synthetic.main.add_course_propose.*
import kotlinx.android.synthetic.main.add_course_propose.description
import kotlinx.android.synthetic.main.info_course.*
import kotlinx.android.synthetic.main.list_course.view.*
import kotlinx.android.synthetic.main.list_course_propose.view.*
import kotlinx.android.synthetic.main.signin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class AddCoursePropose : Fragment() {

    lateinit var categoryId: String
    lateinit var levelId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_course_propose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAction()

        (activity as AppCompatActivity).supportActionBar?.hide()

        val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
        val callCategory = serviceGenerator.getCategory()
        val callLevel = serviceGenerator.getLevel()

        val listCategory = view.findViewById<Spinner>(R.id.spinnerCategory)
        val listLevel = view.findViewById<Spinner>(R.id.spinnerLevel)

        initCategory(callCategory, listCategory, view)
        initLevel(callLevel, listLevel, view)

        btnBackPropose.setOnClickListener {

            val fragment = ListCoursePropose()
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

    fun initCategory(call: Call<MutableList<DataCategory>>, listCategory: Spinner, view: View){
        call.enqueue(object : Callback<MutableList<DataCategory>> {
            override fun onResponse(call: Call<MutableList<DataCategory>>, response: Response<MutableList<DataCategory>>) {
                val size = response.body()?.size

                var libelleCategory : String?
                val array: MutableList<String> = ArrayList()
                if (size != null) {
                    for (i in 0..(size - 1)) {
                        libelleCategory = response?.body()!![i].libelle
                        array?.add(libelleCategory)
                    }
                }

                val arrayAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, array)
                listCategory.adapter = arrayAdapter

                listCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        categoryId = response.body()!![position].id
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                }

            }

            override fun onFailure(call: Call<MutableList<DataCategory>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
    }

    fun initLevel(call: Call<MutableList<DataLevel>>, listLevel: Spinner, view: View){
        call.enqueue(object : Callback<MutableList<DataLevel>> {
            override fun onResponse(call: Call<MutableList<DataLevel>>, response: Response<MutableList<DataLevel>>) {
                val size = response.body()?.size

                var libelleLevel : String?
                val array: MutableList<String> = ArrayList()
                if (size != null) {
                    for (i in 0..(size - 1)) {
                        libelleLevel = response?.body()!![i].libelle
                        array?.add(libelleLevel)
                    }
                }

                val arrayAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, array)
                listLevel.adapter = arrayAdapter

                listLevel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        levelId = response.body()!![position].id
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                }

            }

            override fun onFailure(call: Call<MutableList<DataLevel>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
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
        request.userId = userId
        request.categorieId = categoryId
        request.niveauId = levelId

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.createCoursePropose(request).enqueue(object : Callback<DataCreateCoursePropose> {
            override fun onResponse(call: Call<DataCreateCoursePropose>, response: Response<DataCreateCoursePropose>){
                val fragment = ListCoursePropose()
                val args = Bundle()
                args.putString("userId", userId)
                fragment.arguments = args

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerCoursePropose, fragment)
                    .commitAllowingStateLoss()
            }

            override fun onFailure(call: Call<DataCreateCoursePropose>, t: Throwable) {
                Log.e("Error", "Message :" + t.message)
            }

        })



    }

}
