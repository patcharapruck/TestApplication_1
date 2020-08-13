package com.example.testapplication_1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication_1.dto.login.LoginCollectionDto
import com.example.testapplication_1.dto.register.RegisterCollectionDto
import com.example.testapplication_1.dto.user.UserCollectionDto
import com.example.testapplication_1.http.HttpManager
import com.example.testapplication_1.json.EditUser
import com.example.testapplication_1.json.UserJson
import com.example.testapplication_1.singleton.LoginManager
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ProfileActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    View.OnClickListener {

    private var dto: LoginCollectionDto? = null
    private var dto_user: UserCollectionDto? = null
    private var user_id: Int? = null
    private var login_id: Int? = null
    private var date_calender: Date? = null

    private lateinit var profileUserName: EditText
    private lateinit var profileUserPhone: EditText
    private lateinit var profileUserBirth: Button
    private lateinit var btnSubmit: Button

    @SuppressLint("SimpleDateFormat")
    private var format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    private var date_str: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initInstances()
    }

    private fun initInstances() {

        profileUserName = findViewById(R.id.profile_user_name) as EditText
        profileUserPhone = findViewById(R.id.profile_user_phone) as EditText

        profileUserBirth = findViewById(R.id.profile_user_birth) as Button
        btnSubmit = findViewById(R.id.btn_edit_user) as Button

        dto = LoginManager.getInstance()!!.getLoginDto()

        if (dto != null){

            user_id = dto!!.data!!.userID
            login_id = dto!!.data!!.loginID

            getUser(user_id)
            btnSubmit.setOnClickListener(this)
        }

        profileUserBirth.setOnClickListener(this)

    }

    private fun setEditText() {

        date_calender = dto_user!!.data!!.date
        date_str = format.format(dto_user!!.data!!.date)

        profileUserName.setText(dto_user!!.data!!.name)
        profileUserPhone.setText(dto_user!!.data!!.phone)
        profileUserBirth.text = date_str
    }

    private fun callRegister() {

        val gson = Gson()
        val json_user: UserJson = (
                UserJson(
                    user_id,
                    profileUserName.text.toString(),
                    date_str,
                    profileUserPhone.text.toString(),
                    login_id
                )
                )

        val json = gson.toJson(
            EditUser(
                json_user
            )
        )

        editUser(json)

    }

    private fun showDateDialog() {

        val calendar = Calendar.getInstance()
        calendar.time = date_calender


        val datePickerDialog = DatePickerDialog(
            this,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date_str = "$year-"+(month+1)+"-$dayOfMonth"
        date_calender = format.parse(date_str);
        profileUserBirth.text = date_str
    }

    override fun onClick(v: View?) {

        when(v){
            profileUserBirth -> {
                showDateDialog()
            }
            btnSubmit -> {
                callRegister()
            }
        }

    }

    fun getUser(user_id:Int?) {

        HttpManager.service.getUser(user_id)
            .enqueue(object : Callback<UserCollectionDto> {
                override fun onFailure(call: Call<UserCollectionDto>, t: Throwable) {
                    Log.d("failure",t.message)
                }
                override fun onResponse(
                    call: Call<UserCollectionDto>,
                    response: Response<UserCollectionDto>
                ) {

                    Log.d("login_apiswd",response.body().toString())
                    when {
                        response.isSuccessful -> {
                            dto_user = response.body()
                            when (dto_user!!.status) {
                                200 -> {
                                    setEditText()
                                }
                            }
                        } else -> {
                        Log.d("login_api",response.message())
                    }
                    }
                }
            })
    }

    private fun editUser(json: String?) {

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), json)
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
                                    finish()
                                }
                                else -> Log.d("register_api",dto.status.toString())
                            }
                        }
                        else -> Log.d("register_api",response.message())
                    }
                }
            })
    }
}