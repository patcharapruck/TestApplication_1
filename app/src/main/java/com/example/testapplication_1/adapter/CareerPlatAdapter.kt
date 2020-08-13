package com.example.testapplication_1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication_1.CareerActivity
import com.example.testapplication_1.R
import com.example.testapplication_1.view.modelclass.CareerPlatModelClass

class CareerPlatAdapter (val context: Context ,val items: ArrayList<CareerPlatModelClass>):
    RecyclerView.Adapter<CareerPlatAdapter.CareerPlatHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareerPlatHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.customview_list_career, parent, false)
        return CareerPlatHolder(itemView, items, this)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CareerPlatHolder, position: Int) {
        holder.number.setText((position+1).toString())
        holder.name.setText(items.get(position).career_name)
        holder.salary.setText(items.get(position).career_salary.toString())

        holder.linearlayoutListCareer.setOnClickListener(View.OnClickListener {

            val intent: Intent? = Intent(context, CareerActivity::class.java)
            if (intent != null) {
                intent.putExtra("id_career", items.get(position).career_id)
                context.startActivity(intent)
            }
        })
    }

    class CareerPlatHolder(
        itemsView: View,
        val items: ArrayList<CareerPlatModelClass>,
        val adapter: CareerPlatAdapter
    ) : RecyclerView.ViewHolder(itemsView) {

        lateinit var number: TextView
        lateinit var name: TextView
        lateinit var salary: TextView

        lateinit var linearlayoutListCareer: LinearLayout

        init {
            initInstanes()
        }

        private fun initInstanes() {

            number = itemView.findViewById(R.id.number_career) as TextView
            name = itemView.findViewById(R.id.name_career) as TextView
            salary = itemView.findViewById(R.id.salary_career) as TextView

            linearlayoutListCareer = itemView.findViewById(R.id.linearlayout_list_career) as LinearLayout

        }

    }
}