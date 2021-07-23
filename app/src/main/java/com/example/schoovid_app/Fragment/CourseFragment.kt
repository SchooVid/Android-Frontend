package com.example.schoovid_app.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schoovid_app.ListCourse
import com.example.schoovid_app.ListCoursePropose
import com.example.schoovid_app.R
import kotlinx.android.synthetic.main.fragment_course.*

class CourseFragment(val userId: String) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //val userId = userId

        /*btnListCoursePropose.setOnClickListener {
        val intent = Intent (this.context, ListCoursePropose::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)*/
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = ListCoursePropose()
        val args = Bundle()
        args.putString("userId", userId)
        fragment.arguments = args

        Log.e("TAG890", userId)

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.containerCoursePropose, fragment)
            .commitAllowingStateLoss()

    }
}

