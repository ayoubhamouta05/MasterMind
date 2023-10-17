package com.example.myshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler



class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // This code will be executed after the 2 seconds delay
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000L)
    }
}