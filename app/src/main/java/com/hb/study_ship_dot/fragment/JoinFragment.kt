package com.hb.study_ship_dot.fragment

import android.content.ContentValues.TAG
import android.content.SharedPreferences
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

        var id_check =false
        var pw_check =false
        val Index_file = context?.getSharedPreferences("Index_file", 0)
        var index = Index_file?.getInt("index", 0)
        var count =0

        btn_overlap_join.setOnClickListener { //아이디 중복 체크 버튼을 눌렀을 때
            val login_id=inputId_join.text.toString()

            //저장된 id 확인 용
            val Id_file = context?.getSharedPreferences("Id_file", 0)

            //중복체크
            for(i in 1 ..index!!) {
                if (Id_check?.getString("${i}", "").equals(login_id)) {
                    count=0
                    break
                }
                else{
                    count++
                }
            }
            if(count==0){ //중복인 경우
                Toast.makeText(context, "사용할 수 없는 아이디입니다.", Toast.LENGTH_SHORT).show()
                inputId_join.setText("")
            }
            else{
                Toast.makeText(context, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                id_check = true
            }
        }

        btn_join_join.setOnClickListener { //회원가입 버튼을 눌렀을 때

            val name = inputName_join.text.toString()
            val pn = inputPn_join.text.toString()
            val id = inputId_join.text.toString()
            val pw= inputPw_join.text.toString()
            val pwch=inputPwch_join.text.toString()

            var isExistBlank=false

            //입력되지 않은 정보가 있는 경우
            if(name.isEmpty() || pn.isEmpty() || id.isEmpty() || pw.isEmpty() || pwch.isEmpty()){
                Toast.makeText(context, "회원정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
                isExistBlank=true
            }
            else{ //모든 정보가 입력된 후 비밀번호와 비밀번호 확인이 일치하는지 확인
                if(pw.equals(pwch)){
                    pw_check = true
                    if(!id_check){
                        Toast.makeText(context, "아이디 중복확인이 필요합니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    inputPw_join.setText("")
                    inputPwch_join.setText("")
                }
            }

            if(id_check&&pw_check&&!isExistBlank){ //회원가입 성공(중복확인 완료, 비밀번호 일치, 빈칸 없음)
                //입력한 정보 각 파일에 저장
                val Pw_file = context?.getSharedPreferences("Pw_file", 0)
                val Name_file = context?.getSharedPreferences("Name_file", 0)
                val Pn_file = context?.getSharedPreferences("Pn_file", 0)

                val Index_editor = Index_file?.edit()
                index = index!! + 1 //데이터를 저장할 새로운 인덱스
                Index_editor?.putInt("index", index!!)
                Index_editor?.apply()

                val Name_editor= Name_file?.edit()
                Name_editor?.putString("${index}",name)
                Name_editor?.apply()

                val Pn_editor = Pn_file?.edit()
                Pn_editor?.putString("${index}",pn)
                Pn_editor?.apply()

                val Id_editor =Id_file?.edit()
                Id_editor?.putString("${index}",id)
                Id_editor?.apply()

                val Pw_editor = Pw_file?.edit()
                Pw_editor?.putString("${index}",pw)
                Pw_editor?.apply()


                //가입 완료 메세지
                Toast.makeText(context, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

                //회원가입 성공 후 로그인 화면으로 이동
                navController.navigate(R.id.loginFragment)

            }
        }
    }
}
