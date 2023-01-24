package com.hb.study_ship_dot.Fragment

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hb.study_ship_dot.R
import kotlinx.android.synthetic.main.fragment_chk_info_pw.*


class ChkInfoPw : Fragment() {
    val TAG:String="ChkInfoPw"
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //비밀번호 변경 버튼
        btn_changePw_chkInfoPw.setOnClickListener {
            //editText로부터 입력된 값을 받아옴
            val id=InputId_chkInfoPw.text.toString()
            val name=inputName_chkInfoPw.text.toString()
            val pn=inputPn_chkInfoPw.text.toString()

            //쉐어드로부터 저장된 id,name,pn 가져오기
           //val sharedPreference=getSharedPreferences("Info",Context.MODE_PRIVATE)
            val saveId=MyApplication.prefs.getString("id","")
            val saveName=MyApplication.prefs.getString("name","")
            val savePn=MyApplication.prefs.getString("pn","")
            //사용자가 입력한 값과 쉐어드에서 불러온 값 비교
            if(id == saveId && name==saveName && pn==savePn){
                //회원정보 일치 다이얼로그
                dialog("success")
            }
            else {
                //회원정보 일치X 다이얼로그
                dialog("fail")
            }

        }

    }

    fun dialog(type: String){

        val dialog=AlertDialog.Builder(context)
        if (type.equals("success")){
            dialog.setTitle("회원정보 일치")
            dialog.setMessage("비밀번호 변경 페이지로 넘어갑니다.")
        }
        else if(type.equals("fail")){
            dialog.setTitle("회원정보 불일치")
            dialog.setMessage("회원정보를 다시 입력해주세요.")
        }
        var dialog_listener=object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE -> Log.d(TAG,"")
                }
            }
        }
        dialog.setPositiveButton("확인",dialog_listener)
        dialog.show()
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

        btn_changePw_chkInfoPw.setOnClickListener { //비밀번호 변경 버튼을 눌렀을 때 비밀번호 변경 페이지로 이동
            navController.navigate(R.id.changePwFragment)
        }
    }


}
class MyApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}
class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("Info", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

}