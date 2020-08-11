package com.example.testapplication_1

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.testapplication_1.dto.login.LoginCollectionDto
import com.example.testapplication_1.http.HttpManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var textRegister:TextView
    private lateinit var btnLogin: Button

    private lateinit var user: EditText
    private lateinit var pass: EditText

    fun newInstance():LoginFragment{
        val fragment:LoginFragment = LoginFragment()
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
        val rootView: View = inflater.inflate(R.layout.fragment_login, container, false)
        initInstances(rootView)

        return rootView
    }

    private fun initInstances(rootView: View) {

        btnLogin = rootView.findViewById(R.id.btn_login) as Button
        textRegister = rootView.findViewById(R.id.text_regis) as TextView
        user = rootView.findViewById(R.id.user) as EditText
        pass = rootView.findViewById(R.id.pass) as EditText

        textRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null){

        }
    }

    override fun onClick(v: View?) {

        when(v){
            textRegister -> {
                val fragmentTransaction: FragmentTransaction = this.fragmentManager!!.beginTransaction()
                fragmentTransaction.add(
                    R.id.container,
                    RegisterFragment().newInstance()
                )
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            btnLogin -> {
                loginAPI(user.text.toString(),pass.text.toString())
            }
        }

    }

    fun loginAPI(u:String?,p:String?) {

        HttpManager.service.loginAPI(u,p)
            .enqueue(object : Callback<LoginCollectionDto> {
                override fun onFailure(call: Call<LoginCollectionDto>, t: Throwable) {
                    Log.d("failure",t.message)
                }
                override fun onResponse(
                    call: Call<LoginCollectionDto>,
                    response: Response<LoginCollectionDto>
                ) {

                    Log.d("login_apiswd",response.body().toString())
                    when {
                        response.isSuccessful -> {
                            val dto: LoginCollectionDto? = response.body()
                            when (dto!!.status) {
                                200 -> {
                                    loginSubmit()
                                }
                            }
                        } else -> {
                            Log.d("login_api",response.message())
                        }
                    }
                }
            })
    }

    private fun loginSubmit() {

        val intent: Intent? = Intent(context, HomeActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }
}