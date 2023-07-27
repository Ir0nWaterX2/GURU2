package com.example.guru2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TestActivity : AppCompatActivity() {

    lateinit var tvTest1 : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        //몇번째 질문(몇 일)인지에 대한 값 받기
        val day = intent.getStringExtra("day")

        //위젯과 변수 연결
        tvTest1 = findViewById<TextView>(R.id.tvTest)

        //화면에 값 표시
        tvTest1.text = day


    }
}