package com.example.guru2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class AnswerAdapter(private val itemList: ArrayList<A_item>, private val day: Int) :
        RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    lateinit var dbManager : DBManager
    private var selectedYear: Int = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)

    fun setSelectedYear(year: Int) {
        selectedYear = year
        notifyDataSetChanged()
    }

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_answer_item, parent, false)
        return AnswerViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val defaultLayout: ConstraintLayout = itemView.findViewById(R.id.clDefault)
        private val expandLayout: ConstraintLayout = itemView.findViewById(R.id.clExpand)
        private val tvMonth: TextView = itemView.findViewById(R.id.tvMonth)
        private val tvAnswer: TextView = itemView.findViewById(R.id.tvAnswer)

        init {
            defaultLayout.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = itemList[position]
                    item.isExpanded = !item.isExpanded
                    notifyItemChanged(position)
                }
            }
        }

        fun bind(item: A_item) {
            tvMonth.text = item.month

            if (item.isExpanded) {
                expandLayout.visibility = View.VISIBLE

                // 해당 월과 날짜에 대한 데이터 가져오기
                dbManager = DBManager(itemView.context, "DB", null, 1)
                val currentYear = selectedYear
                val month = item.month.substringBefore(" 월").toInt()
                val day = day + 1 // 전달받은 day 값을 사용

                val data = dbManager.getData(currentYear, month, day)

                tvAnswer.text = data
            } else {
                expandLayout.visibility = View.GONE
            }
        }
    }
}
