package com.example.testapplication_1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentTemplate : Fragment() {

    fun newInstance():FragmentTemplate{
        val fragment:FragmentTemplate = FragmentTemplate()
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