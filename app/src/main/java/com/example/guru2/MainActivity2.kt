package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {

    lateinit var btnToInputActivity : Button
    lateinit var btnToListActivity : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnToInputActivity = findViewById(R.id.btnToInputActivity)
        btnToListActivity = findViewById(R.id.btnToListActivity)

        btnToInputActivity.setOnClickListener{
            val intent = Intent(this, InputActivity::class.java)
            intent.putExtra("FROM_MAIN_ACTIVITY_2", true)
            startActivity(intent)
        }

        btnToListActivity.setOnClickListener{
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }
}