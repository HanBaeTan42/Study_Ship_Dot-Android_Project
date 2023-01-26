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
import kotlinx.android.synthetic.main.fragment_chk_info_pw.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChkInfoPwFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_chk_info_pw, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        btn_changePw_chkInfoPw.setOnClickListener { //비밀번호 변경 버튼을 눌렀을 때

            //editText로부터 입력된 값을 받아옴
            var id = InputId_chkInfoPw.text.toString()
            var name = inputName_chkInfoPw.text.toString()
            var pn = inputPn_chkInfoPw.text.toString()

            //파일들
            val Index_file = context?.getSharedPreferences("Index_file", 0)
            val Name_file = context?.getSharedPreferences("Name_file", 0)
            val Pn_file = context?.getSharedPreferences("Pn_file", 0)
            val Id_file = context?.getSharedPreferences("Id_file", 0)

            //인덱스
            val index = Index_file?.getInt("index", 0)

            //쉐어드로부터 저장된 id,name,pn 가져오기
            val saveId = Id_file?.getString("$index", "")
            val saveName = Name_file?.getString("$index", "")
            val savePn = Pn_file?.getString("$index", "")

            //사용자가 입력한 값과 쉐어드에서 불러온 값 비교
            if (id == saveId && name == saveName && pn == savePn) {
                //회원정보 일치
                Toast.makeText(context, "회원정보가 일치합니다.\n비밀번호 변경 페이지로 이동합니다.", Toast.LENGTH_SHORT)
                //비밀번호 변경 페이지로 이동
                navController.navigate(R.id.changePwFragment)
            } else {
                //회원정보 일치X
                Toast.makeText(context, "회원정보가 일치하지 않습니다.\n다시입력해주세요.", Toast.LENGTH_SHORT)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChkInfoPwFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

