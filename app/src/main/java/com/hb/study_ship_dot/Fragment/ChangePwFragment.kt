package com.hb.study_ship_dot.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hb.study_ship_dot.R
import kotlinx.android.synthetic.main.fragment_change_pw.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChangePwFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChangePwFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun init(){
        val Id_file = context?.getSharedPreferences("Id_file", 0)
        val Pw_file = context?.getSharedPreferences("Pw_file", 0)
        val Index_file = context?.getSharedPreferences("Index_file", 0)
        val Name_file = context?.getSharedPreferences("Name_file", 0)
        val Pn_file = context?.getSharedPreferences("Pn_file", 0)

        val Id_editor = Id_file?.edit()
        Id_editor?.putString("1", "aaa")
        Id_editor?.putString("2", "bbb")
        Id_editor?.apply()

        val Pw_editor = Pw_file?.edit()
        Pw_editor?.putString("1", "asd123")
        Pw_editor?.putString("2", "qwe456")
        Pw_editor?.apply()

        val Index_editor = Index_file?.edit()
        Index_editor?.putInt("index", 2)
        Index_editor?.apply()

        val Name_editor = Name_file?.edit()
        Name_editor?.putString("1", "곽춘배")
        Name_editor?.putString("2", "편덕배")
        Name_editor?.apply()

        val Pn_editor = Pn_file?.edit()
        Pn_editor?.putString("1", "010-1234-1234")
        Pn_editor?.putString("2", "010-5678-5678")
        Pn_editor?.apply()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_pw, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        navController = Navigation.findNavController(view)
        
        btn_changePw_changePw.setOnClickListener { //변경 버튼을 눌렀을 때 두 비밀번호 텍스트가 일치하는 지 확인 후 로그인 화면으로 이동
            val index = 0   // 바꿔줄 비밀번호의 위치

            // EditText에서 입력받은 데이터를 가져옴
            val newPw = inputNewPw_changePw.text.toString()
            val newPw_re = inputNewPw_re_changePw.text.toString()

            // SharedPreferences 타입의 Pw 파일을 가져옴
            val Pw_file = context?.getSharedPreferences("Pw_file", 0)
            val Pw_editor = Pw_file?.edit()     // editor 선언

            // 새 비밀번호와 비밀번호 확인 데이터가 일치하는지 확인
            if(newPw == newPw_re) {
                Toast.makeText(context, "비밀번호 변경 완료", Toast.LENGTH_SHORT).show()
                Pw_editor?.putString("${index}", newPw)
                Pw_editor?.apply()
                navController.navigate(R.id.loginFragment)
            }
            else
                Toast.makeText(context, "비밀번호 입력 오류", Toast.LENGTH_SHORT).show()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Change_PwFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChangePwFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}