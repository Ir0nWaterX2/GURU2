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
    fun getCurrentYear(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.YEAR)
    }

    // 현재 월을 가져오는 함수
    fun getCurrentMonth(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.MONTH) + 1
    }

    // 현재 일을 가져오는 함수
    fun getCurrentDay(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    // 특정 연도의 테이블에 데이터를 삽입하는 함수
    private fun insertData(year: Int, month: Int, day: Int, answer: String) {
        val db = writableDatabase
        db.execSQL("INSERT INTO userDB_$year (month, day, answer) VALUES ($month, $day, '$answer')")
        db.close()
    }

    // 특정 연도의 테이블이 존재하는지 확인하는 함수
    fun isTableExist(year: Int): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='userDB_$year'", null)
        val tableExists = cursor.count > 0
        cursor.close()
        db.close()
        return tableExists
    }

    // 데이터 존재 여부 확인 함수
    fun isDataExist(year: Int, month: Int, day: Int): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM userDB_$year WHERE month = $month AND day = $day"
        val cursor = db.rawQuery(query, null)
        val count = cursor.count
        cursor.close()
        return count > 0
    }

    // 데이터 추가 함수
    fun addData(answer: String) {
        val currentYear = getCurrentYear()
        val currentMonth = getCurrentMonth()
        val currentDay = getCurrentDay()

        if (isTableExist(currentYear)) {
            val isDataExist = isDataExist(currentYear, currentMonth, currentDay)
            if (isDataExist) {
                updateData(currentYear, currentMonth, currentDay, answer)
            } else {
                insertData(currentYear, currentMonth, currentDay, answer)
            }
        } else {
            createYearlyTable(currentYear)
            insertData(currentYear, currentMonth, currentDay, answer)
        }
    }

    // 기존 데이터를 수정하는 함수
    private fun updateData(year: Int, month: Int, day: Int, answer: String) {
        val db = writableDatabase
        db.execSQL("UPDATE userDB_$year SET answer = '$answer' WHERE month = $month AND day = $day")
        db.close()
    }

    // 연도별 테이블 생성 함수
    fun createYearlyTable(year: Int) {
        val db = writableDatabase
        db.execSQL("CREATE TABLE IF NOT EXISTS userDB_$year (month INTEGER, day INTEGER, answer TEXT)")
        db.close()
    }

    // 특정 연도, 월, 일에 해당하는 데이터를 가져오는 함수
    fun getData(year: Int, month: Int, day: Int): String {
        val db = readableDatabase
        val query = "SELECT answer FROM userDB_$year WHERE month = $month AND day = $day"
        val cursor = db.rawQuery(query, null)
        var data = ""

        if (cursor.moveToFirst()) {
            data = cursor.getString(cursor.getColumnIndex("answer"))
        }

        cursor.close()
        db.close()
        return data
    }
}