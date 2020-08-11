package com.example.testapplication_1

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.util.*

class RegisterFragment : Fragment(), View.OnClickListener , DatePickerDialog.OnDateSetListener {

    private lateinit var regisUser: EditText
    private lateinit var regisPass: EditText
    private lateinit var regisUserName: EditText
    private lateinit var regisUserPhone: EditText

    private lateinit var btnUserBirth: Button
    private lateinit var btnRegister: Button

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
        }
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
        val date = "$dayOfMonth-"+(month+1)+"-$year"
        btnUserBirth.text = date
    }
}