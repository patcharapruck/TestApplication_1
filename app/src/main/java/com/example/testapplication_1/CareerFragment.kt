package com.example.testapplication_1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.testapplication_1.dto.career.CareerListItemDto
import com.example.testapplication_1.dto.career.ResCareerCollectionDto
import com.example.testapplication_1.dto.login.LoginCollectionDto
import com.example.testapplication_1.dto.register.RegisterCollectionDto
import com.example.testapplication_1.dto.user.CareerCollectionDto
import com.example.testapplication_1.http.HttpManager
import com.example.testapplication_1.json.CareerJson
import com.example.testapplication_1.json.EditCareer
import com.example.testapplication_1.json.EditUser
import com.example.testapplication_1.json.UserJson
import com.example.testapplication_1.singleton.LoginManager
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CareerFragment : Fragment(), View.OnClickListener {

    private var career_id: Int = 0

    private var add_career_id: Int? = null
    private lateinit var careerName: EditText
    private lateinit var careerSalary: EditText
    private lateinit var btnCareer: Button

    private var dto: LoginCollectionDto? = null
    private var user_id: Int? = null

    fun newInstance(id: Int):CareerFragment{
        val fragment:CareerFragment = CareerFragment()
        fragment.arguments = Bundle().apply {
            // set arguments
            putInt("career_id", id)
        };
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get arguments
        career_id = arguments!!.getInt("career_id")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_second, container, false)
        initInstances(rootView)

        return rootView
    }


    private fun initInstances(rootView: View) {

        careerName = rootView.findViewById(R.id.career_name) as EditText
        careerSalary = rootView.findViewById(R.id.career_salary) as EditText
        btnCareer = rootView.findViewById(R.id.btn_career) as Button

        dto = LoginManager.getInstance()!!.getLoginDto()

        if (dto != null){
            user_id = dto!!.data!!.userID
        }

        if(career_id != 0){
            getCareer(career_id)
            add_career_id = career_id
        }

        btnCareer.setOnClickListener(this)
    }

    private fun setEditTextCareer(data: CareerListItemDto?) {

        careerName.setText(data!!.userCareername)
        careerSalary.setText(data.salary.toString())

    }

    private fun callCareer() {

        val gson = Gson()
        val json_career: CareerJson = (
                CareerJson(
                    add_career_id,
                    careerName.text.toString(),
                    Integer.parseInt(careerSalary.text.toString()),
                    user_id
                )
                )

        val json = gson.toJson(
            EditCareer(
                json_career
            )
        )

        editCareer(json)

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

    fun getCareer(id:Int?) {

        HttpManager.service.getCareer(id)
            .enqueue(object : Callback<CareerCollectionDto> {
                override fun onFailure(call: Call<CareerCollectionDto>, t: Throwable) {
                    Log.d("failure",t.message)
                }
                override fun onResponse(
                    call: Call<CareerCollectionDto>,
                    response: Response<CareerCollectionDto>
                ) {

                    Log.d("career",response.body().toString())
                    when {
                        response.isSuccessful -> {
                            val dto: CareerCollectionDto? = response.body()
                            when (dto!!.status) {
                                200 -> {
                                    setEditTextCareer(dto.data)
                                }
                            }
                        } else -> {
                        Log.d("career",response.message())
                    }
                    }
                }
            })
    }

    private fun editCareer(json: String) {

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), json)
        HttpManager.service.careerAPI(requestBody)
            .enqueue(object : Callback<ResCareerCollectionDto> {
                override fun onFailure(call: Call<ResCareerCollectionDto>, t: Throwable) {
                    Log.d("failure",t.message)
                }

                override fun onResponse(
                    call: Call<ResCareerCollectionDto>,
                    response: Response<ResCareerCollectionDto>
                ) {
                    when {
                        response.isSuccessful -> {
                            val dto: ResCareerCollectionDto? = response.body()
                            when (dto!!.status) {
                                200 -> {
                                    activity!!.finish()
                                }
                                else -> Log.d("add_career",dto.status.toString())
                            }
                        }
                        else -> Log.d("add_career",response.message())
                    }
                }
            })
    }

    override fun onClick(v: View?) {
        when(v){
            btnCareer -> {
                callCareer()
            }
        }
    }

}