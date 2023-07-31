package com.example.guru2
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//리사이클러 뷰 어댑터 클래스
class BoardAdapter(val itemList: ArrayList<Q_item>) :

    RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_list_item, parent, false)
        return BoardViewHolder(view)
    }

    //홀더에 값 전달
    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {

        //리사이클러뷰 하나의 아이템 당 출력될 내용
        holder.tvDay.text = itemList[position].day.toString()
        holder.tvQuestion.text = itemList[position].question
    }


    // 아이템 리스트 개수를 가져오는 함수
    override fun getItemCount(): Int {
        return itemList.count()
    }

    //뷰홀더 클래스
    inner class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // 질문에 해당하는 날짜를 출력하는 TextView 위젯
        val tvDay = itemView.findViewById<TextView>(R.id.tvDay)
        // 질문 내용을 출력하는 TextView 위젯
        val tvQuestion = itemView.findViewById<TextView>(R.id.tvQuestion)

        init{
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, AnswerActivity ::class.java)

                //이동할 Activity에 아이템 정보 전달
                val index = adapterPosition // 몇번째 질문인지에 대한 정보 전달 (범위: 0~30)
                intent.putExtra("day",0+index)

                //아이템(질문 클릭 시 AnswerActivity로 이동)
                itemView.context.startActivity(intent)
            }
        }

    }
}