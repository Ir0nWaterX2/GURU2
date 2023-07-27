package com.example.guru2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val rv_board = findViewById<RecyclerView>(R.id.rv_board)

        //출력될 아이템(질문을 저장할 배열)
        val itemList = ArrayList<Q_item>()

        //31개 질문 출력. 추후 서로 다른 질문 31개로 수정 예정
        for(n in 1..31){
            itemList.add(Q_item("$n 일","$n 번째 질문"))
        }

        val boardAdapter = BoardAdapter(itemList)
        boardAdapter.notifyDataSetChanged()

        rv_board.adapter = boardAdapter
        rv_board.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

}