package com.example.guru2

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment

class YearPickerDialog : DialogFragment() {

    // Callback 인터페이스 정의
    interface OnYearSelectedListener {
        fun onYearSelected(year: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val yearPicker = NumberPicker(requireContext())
        val currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        val years = (1900..currentYear).toList().reversed().map { it.toString() }.toTypedArray()
        yearPicker.minValue = 0
        yearPicker.maxValue = years.size - 1
        yearPicker.displayedValues = years

        return AlertDialog.Builder(requireContext())
                .setTitle("연도 선택")
                .setPositiveButton("확인") { _, _ ->
                    val selectedYear = years[yearPicker.value].toInt()
                    // 연도 선택 결과를 콜백으로 전달
                    val listener = targetFragment as? OnYearSelectedListener
                    listener?.onYearSelected(selectedYear)
                }
                .setNegativeButton("취소") { _, _ ->
                    // 취소 버튼을 눌렀을 때 처리
                }
                .setView(yearPicker)
                .create()
    }
}

