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
import kotlinx.android.synthetic.main.fragment_login.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        btn_login_login.setOnClickListener { //로그인 버튼을 눌렀을때
            // EditText에서 입력받은 텍스트 불러옴
            var Id = inputId_login.text.toString()
            var Pw = inputPw_login.text.toString()

            // Id, Pw, Index 파일 선언
            val Id_file = context?.getSharedPreferences("Id_file", 0)
            val Pw_file = context?.getSharedPreferences("Pw_file", 0)
            val Index_file = context?.getSharedPreferences("Index_file", 0)

            // Index 파일에서 데이터를 저장
            val index = Index_file?.getInt("index", 0)

            // for문을 통해 id를 확인
            for(i in 1..index!!)
                if(Id == Id_file?.getString("${i}", ""))
                    if (Pw == Pw_file?.getString("${i}", "")){
                        // 다음 화면이 없기 때문에 우선 주석 처리를 해놓음
                        // navController.navigate(R.id.)
                        Toast.makeText(context, "환영합니다.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
            // 함수가 종료되지 않을 경우
            Toast.makeText(context, "아이디/비밀번호 입력 오류입니다.", Toast.LENGTH_SHORT).show()
        }

        btn_loginTojoin_login.setOnClickListener { //회원가입 버튼을 눌렀을때 회원가입 페이지로 이동
            navController.navigate(R.id.joinFragment)
        }

        btn_loginTofindId_login.setOnClickListener { //아이디 찾기 버튼을 눌렀을때 아이디 찾기 페이지로 이동
            navController.navigate(R.id.findIdFragment)
        }
        
        btn_loginTofindPw_login.setOnClickListener{ //비밀번호 찾기 버튼을 눌렀을때 비밀번호 찾기 페이지로 이동
            navController.navigate(R.id.chkInfoPwFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}