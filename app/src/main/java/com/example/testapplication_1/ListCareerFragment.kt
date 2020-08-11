package com.example.testapplication_1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication_1.adapter.CareerPlatAdapter
import com.example.testapplication_1.view.modelclass.CareerPlatModelClass

class ListCareerFragment : Fragment() {

    private lateinit var recyclerCareer: RecyclerView
    private lateinit var itemCareer:ArrayList<CareerPlatModelClass>
    private lateinit var careerPlatAdapter:CareerPlatAdapter

    fun newInstance():ListCareerFragment{
        val fragment:ListCareerFragment = ListCareerFragment()
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
        val rootView: View = inflater.inflate(R.layout.fragment_first, container, false)
        initInstances(rootView)

        return rootView
    }

    private fun initInstances(rootView: View) {

        recyclerCareer = rootView.findViewById(R.id.recycler_career) as RecyclerView
        setRecyclerView()
    }

    private fun setRecyclerView() {

        itemCareer = ArrayList()
        careerPlatAdapter = CareerPlatAdapter(context!!,itemCareer)
        recyclerCareer.layoutManager = LinearLayoutManager(context!!,
            LinearLayoutManager.VERTICAL,false)
        recyclerCareer.adapter = careerPlatAdapter


        for (i in 0 ..(10 - 1)){
            itemCareer.add(
                CareerPlatModelClass(
                    (i+1)+1,
                    "อาชีพ"+(i+1),
                    (i+1)*10000
                )
            )
            careerPlatAdapter.notifyDataSetChanged()
        }
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