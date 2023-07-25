package com.example.guru2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

class DBManager(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    // 현재 연도를 가져오는 함수
    private fun getCurrentYear(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.YEAR)
    }

    // 현재 월을 가져오는 함수
    private fun getCurrentMonth(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.MONTH) + 1
    }

    // 현재 일을 가져오는 함수
    private fun getCurrentDay(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    // 특정 연도의 테이블에 데이터를 삽입하는 함수
    fun insertData(year: Int, month: Int, day: Int, answer: String) {
        val db = writableDatabase
        db.execSQL("INSERT INTO personnel_$year (month, day, answer) VALUES ($month, $day, '$answer')")
        db.close()
    }

    // 특정 연도의 테이블이 존재하는지 확인하는 함수
    fun isTableExist(year: Int): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='personnel_$year'", null)
        val tableExists = cursor.count > 0
        cursor.close()
        db.close()
        return tableExists
    }

    // 데이터 추가 함수
    fun addData(answer: String) {
        val currentYear = getCurrentYear()
        if (isTableExist(currentYear)) {
            val currentMonth = getCurrentMonth()
            val currentDay = getCurrentDay()
            insertData(currentYear, currentMonth, currentDay, answer)
        } else {
            createYearlyTable(currentYear)
            val currentMonth = getCurrentMonth()
            val currentDay = getCurrentDay()
            insertData(currentYear, currentMonth, currentDay, answer)
        }
    }

    // 연도별 테이블 생성 함수
    private fun createYearlyTable(year: Int) {
        val db = writableDatabase
        db.execSQL("CREATE TABLE IF NOT EXISTS personnel_$year (month INTEGER, day INTEGER, answer TEXT)")
        db.close()
    }
}