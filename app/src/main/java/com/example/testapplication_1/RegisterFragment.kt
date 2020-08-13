package com.example.testapplication_1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.testapplication_1.dto.register.RegisterCollectionDto
import com.example.testapplication_1.http.HttpManager
import com.example.testapplication_1.json.LoginJson
import com.example.testapplication_1.json.RegisterJson
import com.example.testapplication_1.json.UserJson
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class RegisterFragment : Fragment(), View.OnClickListener , DatePickerDialog.OnDateSetListener {

    private lateinit var regisUser: EditText
    private lateinit var regisPass: EditText
    private lateinit var regisUserName: EditText
    private lateinit var regisUserPhone: EditText

    private lateinit var btnUserBirth: Button
    private lateinit var btnRegister: Button

    @SuppressLint("SimpleDateFormat")
    var format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    private var date: Date? = null
    private var date_str: String = ""

    fun newInstance():RegisterFragment{
        val fragment:RegisterFragment = RegisterFragment()
        fragment.arguments = Bundle().apply {
            // set arguments
        };
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get arguments
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_register, container, false)
        initInstances(rootView)

        return rootView
    }

    private fun initInstances(rootView: View) {

        regisUser = rootView.findViewById(R.id.regis_user) as EditText
        regisPass = rootView.findViewById(R.id.regis_pass) as EditText
        regisUserName = rootView.findViewById(R.id.regis_user_name) as EditText
        regisUserPhone = rootView.findViewById(R.id.regis_user_phone) as EditText

        btnUserBirth = rootView.findViewById(R.id.btn_user_birth) as Button
        btnRegister = rootView.findViewById(R.id.btn_register) as Button

        btnUserBirth.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // save stances to bundle
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null){

        }
    }

    override fun onClick(v: View?) {

        when(v){
            btnUserBirth -> {
                showDateDialog()
            }
            btnRegister -> {
                callRegister()
            }
        }
    }

    private fun callRegister() {

        val gson = Gson()

        val json_login : LoginJson = (
            LoginJson(
                regisUser.text.toString(),
                regisPass.text.toString()
            )
        )

        val json_user: UserJson = (
            UserJson(
                null,
                regisUserName.text.toString(),
                date_str,
                regisUserPhone.text.toString(),
                null
            )
        )

        val json_register = gson.toJson(
            RegisterJson(
                json_login,
                json_user
            )
        )

        registerAPI(json_register)

    }

    private fun registerAPI(jsonRegister: String?) {

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), jsonRegister)
        HttpManager.service.registerAPI(requestBody)
            .enqueue(object : Callback<RegisterCollectionDto> {
                override fun onFailure(call: Call<RegisterCollectionDto>, t: Throwable) {
                    Log.d("failure",t.message)
                }

                override fun onResponse(
                    call: Call<RegisterCollectionDto>,
                    response: Response<RegisterCollectionDto>
                ) {
                    when {
                        response.isSuccessful -> {
                            val dto: RegisterCollectionDto? = response.body()
                            when (dto!!.status) {
                                200 -> {
                                    Log.d("register_api","asdasdasdasdasdasd")
                                }
                                else -> Log.d("register_api",dto.status.toString())
                            }
                        }
                        else -> Log.d("register_api",response.message())
                    }
                }
            })
    }

    private fun showDateDialog() {
        val datePickerDialog = DatePickerDialog(
            context!!,
            this,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date_str = "$year-"+(month+1)+"-$dayOfMonth"
        date = format.parse(date_str);
        btnUserBirth.text = date_str
    }
}