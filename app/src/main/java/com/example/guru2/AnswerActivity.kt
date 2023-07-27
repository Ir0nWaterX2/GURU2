package com.example.guru2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val rvAnswer = findViewById<RecyclerView>(R.id.rvAnswer)

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

}