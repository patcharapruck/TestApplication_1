package com.example.testapplication_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class CareerFragment : Fragment() {

    private var career_id: Int = 0

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
        // findViewById
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
}