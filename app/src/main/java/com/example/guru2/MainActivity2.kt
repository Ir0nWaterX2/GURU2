package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {

    private lateinit var dbManager: DBManager

    //InputActivity 이동 버튼
    lateinit var btnToInputActivity : Button
    //ListActivity 이동 버튼
    lateinit var btnToListActivity : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        dbManager = DBManager(this, "DB", null, 1)

        btnToInputActivity = findViewById<Button>(R.id.btnToInputActivity)
        btnToListActivity = findViewById<Button>(R.id.btnToListActivity)

        //MainActivity2에서 InputActivity로 이동 (기존 답변 수정)
        btnToInputActivity.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            intent.putExtra("FROM_MAIN_ACTIVITY_2", true) // Flag 전달
            intent.putExtra("YEAR", dbManager.getCurrentYear())
            intent.putExtra("MONTH", dbManager.getCurrentMonth())
            intent.putExtra("DAY", dbManager.getCurrentDay())
            startActivityForResult(intent, 1)
        }

        //MainActivity2에서 ListActivity로 이동
        btnToListActivity.setOnClickListener {
            var intent = Intent(this,ListActivity ::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Flag 확인
            val isComingBackFromMainActivity2 = data?.getBooleanExtra("FROM_MAIN_ACTIVITY_2", false)

            if (isComingBackFromMainActivity2 == true) {
                // 이전 데이터를 InputActivity에 표시하기 위해 displayPreviousData 호출
                val year = data.getIntExtra("YEAR", -1)
                val month = data.getIntExtra("MONTH", -1)
                val day = data.getIntExtra("DAY", -1)

                // InputActivity로 결과를 돌려주기 위해 Intent 생성
                val inputActivityIntent = Intent(this, InputActivity::class.java)
                inputActivityIntent.putExtra("FROM_MAIN_ACTIVITY_2", true)
                inputActivityIntent.putExtra("YEAR", year)
                inputActivityIntent.putExtra("MONTH", month)
                inputActivityIntent.putExtra("DAY", day)

                // 결과를 돌려주기 위해 startActivityForResult() 사용
                startActivityForResult(inputActivityIntent, 2)
            }
        }
    }

}
