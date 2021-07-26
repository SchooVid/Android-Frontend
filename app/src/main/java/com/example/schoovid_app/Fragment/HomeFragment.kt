package com.example.schoovid_app.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.schoovid_app.ListCourse
import com.example.schoovid_app.MainActivity
import com.example.schoovid_app.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment(val userId: String) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*val userId = userId

        btnListCourse.setOnClickListener {
            val intent = Intent (this.context, ListCourse::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }*/

        /*Log.e("TAG",  )

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container1, ListCourse())
            .commitAllowingStateLoss()*/

    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = ListCourse()
        val args = Bundle()
        args.putString("userId", userId)
        fragment.arguments = args

        Log.e("TAG", userId)

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container1, fragment)
            .commitAllowingStateLoss()

    }

}