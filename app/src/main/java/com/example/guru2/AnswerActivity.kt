package com.example.guru2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList


class AnswerActivity : AppCompatActivity() {

    private lateinit var answerAdapter: AnswerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        var btnBack: ImageButton = findViewById(R.id.btnBack)
        val rvAnswer = findViewById<RecyclerView>(R.id.rvAnswer)
        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)

        // 뒤로가기 버튼
        btnBack.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        val builder = AlertDialog.Builder(this)
        val dbManager = DBManager(this, "DB", null, 1)

        val minYear = dbManager.getMinYear() // 데이터가 존재하는 최소 연도를 가져와서 저장
        val currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        var selectedYear = currentYear // 팝업에 띄울 초깃값은 현재 연도로

        // 연도 선택 버튼 클릭시
        btnDatePicker.setOnClickListener {
            // 연도 선택하는 팝업의 레이아웃을 View 객체로 가져오기
            val inflater = LayoutInflater.from(this)
            val popupView = inflater.inflate(R.layout.dialog_datepicker, null)
            builder.setCustomTitle(popupView)

            // View 객체에서 NumberPicker를 찾아서 선택할 수 있는 연도의 최소/최댓값 설정
            val YearPicker = popupView.findViewById<NumberPicker>(R.id.YearPicker)
            YearPicker.minValue = minYear
            YearPicker.maxValue = currentYear

            // 선택 범위가 돌지 않도록 설정
            YearPicker.wrapSelectorWheel = false

            // 이전에 선택했던 연도가 선택된 채 뜨도록 설정
            YearPicker.value = selectedYear

            val btnSelect = popupView.findViewById<Button>(R.id.btnSelect)
            val btnCancle = popupView.findViewById<Button>(R.id.btnCancle)

            // 팝업창 생성
            val alertDialog = builder.create()

            // 확인 버튼 클릭시
            btnSelect.setOnClickListener {

                // 선택한 연도 값을 가져와서 selectedYear에 저장
                selectedYear = YearPicker.value

                if (!dbManager.isTableExist(selectedYear)) {
                    // 데이터가 존재하지 않는 연도인 경우 Toast 메시지 출력
                    Toast.makeText(this, "선택한 연도에는 데이터가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    // btnDatePicker의 텍스트를 선택한 연도 값으로 설정
                    btnDatePicker.text = selectedYear.toString()

                    // 선택한 연도 값을 AnswerAdapter에 전달하여 업데이트
                    answerAdapter.setSelectedYear(selectedYear)

                    alertDialog.dismiss() // 팝업 닫기
                }
            }

            // 취소 버튼 클릭시
            btnCancle.setOnClickListener {
                // 취소 버튼 클릭 시 동작할 내용?.. 뭐가있지..

                alertDialog.dismiss() // 팝업 닫기
            }

            alertDialog.show() // 팝업창 보이기
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
        tvQuestion.text = "Day ${day+1} \n"+arrQuestion[day]

        //출력될 아이템(월을 저장할 배열)
        val itemList = ArrayList<A_item>()

        //12개의 월 출력
        for(n in 1..12){
            itemList.add(A_item("$n 월"))
        }

        // day 값을 받아온 뒤 AnswerAdapter 생성자에 전달
        val dayValue = intent.getIntExtra("day", 0)
        answerAdapter = AnswerAdapter(itemList, dayValue)

        // 어댑터 및 레이아웃 매니저 설정
        rvAnswer.adapter = answerAdapter
        rvAnswer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
