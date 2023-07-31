package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import java.util.*

class InputActivity : AppCompatActivity() {

    lateinit var dbManager : DBManager

    lateinit var etInput : EditText //답변 내용 출력
    lateinit var btnInput : Button //입력 버튼
    lateinit var btnBack : ImageButton //이전 버튼
    lateinit var tvTodayQuestion : TextView //상단 질문 출력


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        etInput = findViewById(R.id.etInput)
        btnInput = findViewById(R.id.btnInput)
        btnBack = findViewById(R.id.btnBack)
        tvTodayQuestion = findViewById(R.id.tvTodayQuestion)

        //각 날짜에 해당하는 질문(총31개)가 들어있는 배열
        val arrQuestion = arrayOf(
                "당신은 어떤 사람인가요?",
                "오늘 처음으로 먹은 것은 무엇인가요?",
                "최근 들었던 노래 중 좋다고 생각한 노래가 있나요?",
                "한달 안에 했던 가장 착한 일은 무엇인가요?",
                "최근에 들었던 기분 좋은 말이 있나요?",
                "어제 잠들기 전에 한 생각은 무엇인가요?",
                "오늘의 감정은 어땠나요?",
                "지금 가장 먹고싶은 음식은 무엇인가요?",
                "한 달 동안 가장 기억에 남는 순간은 언제인가요?",
                "최근 재미있게 본 영화나 드라마는 무엇인가요?",
                "요즘 나를 속상하게 한 것은 무엇인가요?",
                "오늘 점심 메뉴는 무엇이었나요?",
                "지금 당장 만나고 싶은 사람은 누구인가요?",
                "요즘 나를 가장 힘들게 하는 것은 무엇인가요?",
                "가장 싫어하는 음식은 무엇인가요?",
                "최근 한 소비 중 가장 만족했던 건 무엇인가요?",
                "가장 최근 만든 요리는 무엇인가요?",
                "요즘 나를 웃게 하는 것은 무엇인가요?",
                "한 달 동안 가장 멀리 떠났던 곳은 어디인가요?",
                "지금 나에게 해주고 싶은 말은 무엇인가요?",
                "요즘 나를 행복하게 한 것은 무엇인가요?",
                "가장 좋아하는 음식은 무엇인가요?",
                "요즘 가장 불안하거나 걱정되는 것이 있나요?",
                "최근 새롭게 알게 된 사실이 있나요?",
                "요즘 내가 가장 흥미를 가지고 있는 것은 무엇인가요?",
                "한 달 동안 가장 기억에 남는 사람은 누구인가요?",
                "지금 당장 떠난다면 가고싶은 곳은 어디인가요?",
                "한 달 동안 가장 즐거웠던 일은 무엇인가요?",
                "내가 한 달 동안 가장 소중하게 여긴 것은 무엇인가요?",
                "최근 가장 충격적이었던 일은 무엇인가요?",
                "미래에 있는 나에게 하고 싶은 말이 있나요?"
        )

        // 현재 날짜 정보 가져오기
        val calendar: Calendar = Calendar.getInstance()

        // 현재 날짜에서 '일' 정보 가져오기
        val today: Int = calendar.get(Calendar.DAY_OF_MONTH)
        tvTodayQuestion.text = "Day ${today}" + "    " + arrQuestion[today-1]


        // 뒤로가기 버튼
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        dbManager = DBManager(this, "DB", null, 1)

        // '입력'버튼
        btnInput.setOnClickListener{
            val userAnswer = etInput.text.toString()

            // 사용자가 입력한 데이터를 DB에 추가 또는 수정
            dbManager.addData(userAnswer)

            // 데이터 추가 후 EditText 비움
            etInput.text.clear()

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

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

    }

    private fun displayPreviousData(year: Int, month: Int, day: Int) {
        // DB에 오늘 입력한 데이터 있는지 확인
        val isDataExist = dbManager.isDataExist(year, month, day)

        if (isDataExist) {
            // 지정 날짜에 데이터가 있는 경우, 이 데이터를 가져와 etInput에 표시
            val data = dbManager.getData(year, month, day)
            etInput.setText(data)
        } else {
            // 없는 경우 etInput clear
            etInput.text.clear()
        }
    }
}








