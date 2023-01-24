package com.hb.study_ship_dot.fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hb.study_ship_dot.R
import kotlinx.android.synthetic.main.fragment_join.*
class JoinFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            Log.d(TAG,"아이디 중복 확인 버튼 클릭")

            val id=inputId_join.text.toString()

            //

        }
        
        btn_join_join.setOnClickListener { //회원가입 버튼을 눌렀을 때 로그인 페이지로 이동
            Log.d(TAG,"회원가입 버튼 클릭")

            val name = inputName_join.text.toString()
            val pn = inputPn_join.text.toString()
            val id = inputId_join.text.toString()
            val pw= inputPw_join.text.toString()
            val pwch=inputPwch_join.text.toString()

            if(){ //회원가입 성공(중복확인 완료, 비밀번호 일치 포함)
                //가입 완료 메세지
                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

                //쉐어드에 입력한 정보 저장
                val sharedPreference = activity?.getSharedPreferences("info", Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putString("name",name)
                editor.putString("pn",pn)
                editor.putString("id",id)
                editor.putString("pw",pw)
                editor.putString("pwch",pwch)
                editor.apply()

                navController.navigate(R.id.loginFragment)

            }
        }
    }
}