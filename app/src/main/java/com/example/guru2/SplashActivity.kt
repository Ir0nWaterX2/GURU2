package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


// 로딩창 구현 클래스
class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 1500  //딜레이 초설정 (현재 1.5초입니다!)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // 1.5초 후 실행되는 부분
            startActivity(Intent(this, MainActivity::class.java))
            // MainActivity로 이동!
            finish()
        }, SPLASH_TIME_OUT)


    }
}