package com.hb.study_ship_dot.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hb.study_ship_dot.R
import kotlinx.android.synthetic.main.fragment_join.*
class JoinFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_join)

        btn_join_join.setOnClickListener{
            Log.d(TAG,"회원가입 버튼 클릭")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        btn_overlap_join.setOnClickListener { //아이디 중복 체크 버튼을 눌렀을 때 ??

        }
        
        btn_join_join.setOnClickListener { //회원가입 버튼을 눌렀을 때 로그인 페이지로 이동
            navController.navigate(R.id.loginFragment)
        }
    }
}