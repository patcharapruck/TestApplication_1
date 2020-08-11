package com.example.testapplication_1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .add(R.id.container_home,ListCareerFragment().newInstance())
            .commit()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

            val intent: Intent? = Intent(this, CareerActivity::class.java)
            if (intent != null) {
                intent.putExtra("id_career", 0)
                startActivity(intent)
            }
        }

        initInstances()
    }

    private fun initInstances() {

        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.tab_home_profile -> {

                val intent: Intent? = Intent(this, ProfileActivity::class.java)
                startActivity(intent)

                return true
            }
            R.id.tab_home_logout -> {

                val intent: Intent? = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}