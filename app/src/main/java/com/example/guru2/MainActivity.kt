package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var dbManager: DBManager

    lateinit var btnToInputActivity : Button
    lateinit var btnToListActivity : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbManager = DBManager(this, "DB", null, 1)

        // 현재 날짜 가져오기
        val currentYear = dbManager.getCurrentYear()
        val currentMonth = dbManager.getCurrentMonth()
        val currentDay = dbManager.getCurrentDay()

        // 현재 날짜 데이터 존재 확인
        val todayData = dbManager.getData(currentYear, currentMonth, currentDay)

        if (todayData.isNotEmpty()) {
            // 오늘의 데이터 존재하는 경우 MainActivity2 이동
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료 및 MainActivity2 시작화면 설정
        }

        btnToInputActivity = findViewById(R.id.btnToInputActivity)
        btnToListActivity = findViewById(R.id.btnToListActivity)

        btnToInputActivity.setOnClickListener{
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

        btnToListActivity.setOnClickListener{
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}