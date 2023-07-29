package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var dbManager : DBManager

    //InputActivity 이동 버튼
    lateinit var btnToInputActivity : Button
    //ListActivity 이동 버튼
    lateinit var  btnToListActivity : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DB에 오늘 입력한 데이터가 있는지 확인
        dbManager = DBManager(this, "DB", null, 1)
        val currentYear = dbManager.getCurrentYear()
        val currentMonth = dbManager.getCurrentMonth()
        val currentDay = dbManager.getCurrentDay()

        // 해당 연도의 테이블이 있는지 확인
        if (!dbManager.isTableExist(currentYear)) {
            dbManager.createYearlyTable(currentYear)
        }

        // 해당 날짜의 데이터가 존재하는지 확인
        val isDataExist = dbManager.isDataExist(currentYear, currentMonth, currentDay)

        if (isDataExist) {
            // 오늘 입력한 데이터가 있는 경우 MainActivity2 표시
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        } else{ // 없는 경우 MainActivity 표시
            setContentView(R.layout.activity_main)

            btnToInputActivity = findViewById<Button>(R.id.btnToInputActivity)
            btnToListActivity = findViewById<Button>(R.id.btnToListActivity)

            //MainActivity에서 InputActivity로 이동( 새로 입력)
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
}

