package com.example.myshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.myshop.R
import com.example.myshop.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SplashActivity : AppCompatActivity() {
    lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        CoroutineScope(Dispatchers.IO).launch {
            // This code will be executed after the 2 seconds delay
            delay(2500L)
            val intent = Intent(this@SplashActivity, SelectLevelActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}