package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class InputActivity : AppCompatActivity() {

    lateinit var dbManager : DBManager

    lateinit var etInput : EditText
    lateinit var btnInput : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        etInput = findViewById(R.id.etInput)
        btnInput = findViewById(R.id.btnInput)

        dbManager = DBManager(this, "DB", null, 1)

        btnInput.setOnClickListener{
            val userAnswer = etInput.text.toString()

            // 사용자가 입력한 데이터를 DB에 추가
            dbManager.addData(userAnswer)

            // 데이터 추가 후 EditText 비움
            etInput.text.clear()

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // MainActivity2로부터 전달받은 Intent 가져오기
        val intentFromMainActivity2 = intent

        // MainActivity2로부터 전달받은 Intent에 포함된 플래그 값 확인
        val isComingBackFromMainActivity2 = intentFromMainActivity2.getBooleanExtra("FROM_MAIN_ACTIVITY_2", false)

        // isComingBackFromMainActivity2가 true인 경우에만 이전에 입력한 데이터를 가져와서 설정
        if (isComingBackFromMainActivity2) {
            displayPreviousData()
        }
    }

    private fun displayPreviousData() {

        val currentYear = dbManager.getCurrentYear()
        val currentMonth = dbManager.getCurrentMonth()
        val currentDay = dbManager.getCurrentDay()

        // DB에서 해당 날짜의 데이터를 가져오기
        val data = dbManager.getData(currentYear, currentMonth, currentDay)

        // 가져온 데이터를 etInput에 설정
        etInput.setText(data)
    }
}