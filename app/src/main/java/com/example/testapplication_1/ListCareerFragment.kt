package com.example.testapplication_1

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication_1.adapter.CareerPlatAdapter
import com.example.testapplication_1.dto.career.CareerListItemDto
import com.example.testapplication_1.dto.login.LoginCollectionDto
import com.example.testapplication_1.dto.user.CareerListCollectionDto
import com.example.testapplication_1.helper.HelperListCareer
import com.example.testapplication_1.helper.MyButton
import com.example.testapplication_1.http.HttpManager
import com.example.testapplication_1.listener.MyButtonListener
import com.example.testapplication_1.singleton.LoginManager
import com.example.testapplication_1.view.modelclass.CareerPlatModelClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCareerFragment : Fragment() {

    private lateinit var recyclerCareer: RecyclerView
    private lateinit var itemCareer: ArrayList<CareerPlatModelClass>
    private lateinit var careerPlatAdapter: CareerPlatAdapter

    private var dto: LoginCollectionDto? = null
    private var user_id: Int? = null

    private lateinit var searchText: EditText

    fun newInstance(): ListCareerFragment {
        val fragment: ListCareerFragment = ListCareerFragment()
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
        searchText = rootView.findViewById(R.id.search_text) as EditText
        dto = LoginManager.getInstance()!!.getLoginDto()

        if (dto != null) {
            user_id = dto!!.data!!.userID
        }

        searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getCareerList(user_id, searchText.text.toString())
            }

        })
    }

    override fun onStart() {
        super.onStart()
        getCareerList(user_id, searchText.text.toString())
    }

    private fun setRecyclerView(data: List<CareerListItemDto?>) {

        itemCareer = ArrayList()
        careerPlatAdapter = CareerPlatAdapter(context!!, itemCareer)
        recyclerCareer.setHasFixedSize(true)
        recyclerCareer.layoutManager = LinearLayoutManager(
            context!!,
            LinearLayoutManager.VERTICAL, false
        )

        recyclerCareer.adapter = careerPlatAdapter

        val size: Int = data.size
        for (i in 0..(size - 1)) {
            itemCareer.add(
                CareerPlatModelClass(
                    data.get(i)!!.userCareerID,
                    data.get(i)!!.userCareername,
                    data.get(i)!!.salary
                )
            )
            careerPlatAdapter.notifyDataSetChanged()
        }

        val swipe = object : HelperListCareer(activity!!, recyclerCareer, 150) {
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                buffer.add(
                    MyButton(
                        activity!!,
                        "",
                        30,
                        R.drawable.ic_action_delete,
                        Color.parseColor("#FF3C30"),
                        object : MyButtonListener{
                            override fun OnClick(pos: Int) {
                                Log.d("delete",""+ itemCareer.get(pos).career_id)
                            }

                        }
                    )
                )
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // save stances to bundle
    }

    fun getCareerList(id: Int?, str: String) {

        HttpManager.service.getCareerList(id, str)
            .enqueue(object : Callback<CareerListCollectionDto> {
                override fun onFailure(call: Call<CareerListCollectionDto>, t: Throwable) {
                    Log.d("failure", t.message)
                }

                override fun onResponse(
                    call: Call<CareerListCollectionDto>,
                    response: Response<CareerListCollectionDto>
                ) {
                    when {
                        response.isSuccessful -> {
                            val dto_list: CareerListCollectionDto? = response.body()
                            when (dto_list!!.status) {
                                200 -> {
                                    setRecyclerView(dto_list!!.data)
                                }
                            }
                        }
                        else -> {
                            Log.d("career_list", response.message())
                        }
                    }
                }
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {

        }
    }
}