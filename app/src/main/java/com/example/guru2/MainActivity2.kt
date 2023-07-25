package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {

    //InputActivity 이동 버튼
    lateinit var btnToInputActivity : Button
    //ListActivity 이동 버튼
    lateinit var  btnToListActivity : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnToInputActivity = findViewById<Button>(R.id.btnToInputActivity)
        btnToListActivity = findViewById<Button>(R.id.btnToListActivity)

        //MainActivity에서 InputActivity로 이동 (기존 답변 수정)
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