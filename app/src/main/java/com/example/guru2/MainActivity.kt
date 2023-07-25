package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    //InputActivity 이동 버튼
    lateinit var btnToInputActivity : Button
    //InputActivity 이동 버튼
    lateinit var  btnToListActivity : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToInputActivity = findViewById<Button>(R.id.btnToInputActivity)
        btnToListActivity = findViewById<Button>(R.id.btnToListActivity)

        //MainActivity에서 InputActivity로 이동
        btnToInputActivity.setOnClickListener {
            var intent = Intent(this, InputActivity::class.java)
            startActivity(intent) }

        //MainActivity에서 ListActivity로 이동
        btnToListActivity.setOnClickListener {
                var intent = Intent(this,ListActivity ::class.java)
                startActivity(intent)
        }

    }


}