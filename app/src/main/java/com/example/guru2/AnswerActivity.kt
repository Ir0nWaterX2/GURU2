package com.example.guru2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList


class AnswerActivity : AppCompatActivity(), YearPickerDialog.OnYearSelectedListener {
    private lateinit var btnDatePicker: Button


    lateinit var btnBack : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        var btnBack: ImageButton = findViewById(R.id.btnBack)
        btnDatePicker = findViewById(R.id.btnDatePicker)
        val rvAnswer = findViewById<RecyclerView>(R.id.rvAnswer)
        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        btnBack = findViewById<ImageButton>(R.id.btnBack)



        // 뒤로가기 버튼
        btnBack.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 연도 선택 버튼
        btnDatePicker.setOnClickListener {
            val yearPickerDialog = YearPickerDialog()
            yearPickerDialog.show(supportFragmentManager, "yearPicker")
        }

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

        //몇번째 질문(몇 일)인지에 대한 값 받기
        val day = intent.getIntExtra("day",0)
        tvQuestion.text = "-${day+1}일의 질문- \n"+arrQuestion[day]

        //출력될 아이템(월을 저장할 배열)
        val itemList = ArrayList<A_item>()

        //12개의 월 출력
        for(n in 1..12){
            itemList.add(A_item("$n 월"))
        }


        val AnswerAdapter = AnswerAdapter(itemList)
        AnswerAdapter.notifyDataSetChanged()

        rvAnswer.adapter = AnswerAdapter
        rvAnswer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onYearSelected(year: Int) {
        btnDatePicker.text = year.toString() // btnDatePicker 버튼의 텍스트 변경
    }

}
