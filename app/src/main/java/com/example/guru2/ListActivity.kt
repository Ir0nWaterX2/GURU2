package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ListActivity : AppCompatActivity() {

    //MainActivity 이동 버튼
    lateinit var  btnBack : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        btnBack = findViewById<Button>(R.id.btnBack)

        //listActivity에서 MainActivity로 이동
        btnBack.setOnClickListener {
            var intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)
        }


    }
}