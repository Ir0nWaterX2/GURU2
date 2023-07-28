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

        // MainActivity2로부터 전달받은 Intent 가져오기
        val intentFromMainActivity2 = intent

        // MainActivity2로부터 전달받은 Intent에 포함된 플래그 값 확인
        val isComingBackFromMainActivity2 = intentFromMainActivity2.getBooleanExtra("FROM_MAIN_ACTIVITY_2", false)

        // isComingBackFromMainActivity2가 true인 경우에만 이전에 입력한 데이터를 가져와서 설정
        if (isComingBackFromMainActivity2) {
            val year = intentFromMainActivity2.getIntExtra("YEAR", -1)
            val month = intentFromMainActivity2.getIntExtra("MONTH", -1)
            val day = intentFromMainActivity2.getIntExtra("DAY", -1)
            displayPreviousData(year, month, day)
        }

        btnInput.setOnClickListener {
            val userAnswer = etInput.text.toString()

            // 사용자가 입력한 데이터를 DB에 추가 또는 수정
            dbManager.addData(userAnswer)

            // 데이터 추가 후 EditText 비움
            etInput.text.clear()

            // InputActivity 종료하여 결과를 돌려줌
            finish()
        }
    }

    private fun displayPreviousData(year: Int, month: Int, day: Int) {
        // DB에 오늘 입력한 데이터 있는지 확인
        val isDataExist = dbManager.isDataExist(year, month, day)

        if (isDataExist) {
            // 지정 날짜에 데이터가 있는 경우, 이 데이터를 가져와 etInput에 표시
            val data = dbManager.getData(year, month, day)
            etInput.setText(data)
        } else {
            // 없는 경우, etInput clear
            etInput.text.clear()
        }
    }
}







