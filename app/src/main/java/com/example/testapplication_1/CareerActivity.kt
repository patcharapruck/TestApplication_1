package com.example.testapplication_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CareerActivity : AppCompatActivity() {

    private var id: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_career)

        id = intent.getIntExtra("id_career",0)

        supportFragmentManager.beginTransaction()
            .add(R.id.container_career,CareerFragment().newInstance(id))
            .commit()

    }
}