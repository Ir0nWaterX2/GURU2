package com.example.guru2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class AnswerAdapter(private val itemList: ArrayList<A_item>) :
    RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

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
                tvAnswer.text = "${item.month}의 답변 내용"
            } else {
                expandLayout.visibility = View.GONE
            }
        }
    }
}
