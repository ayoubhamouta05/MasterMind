package com.example.myshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myshop.R
import com.example.myshop.databinding.ActivitySelectLevelBinding
import com.example.myshop.util.Constants.EASY
import com.example.myshop.util.Constants.EXPERT
import com.example.myshop.util.Constants.HARD
import com.example.myshop.util.Constants.LEVEL_KEY
import com.example.myshop.util.Constants.MEDIUM

class SelectLevelActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySelectLevelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectLevel()

    }

    private fun selectLevel(){
        val intent = Intent(this , PlayActivity :: class.java)
        binding.btnEasy.setOnClickListener {
            intent.putExtra(LEVEL_KEY , EASY)
            startActivity(intent)
        }
        binding.btnMedium.setOnClickListener {
            intent.putExtra(LEVEL_KEY , MEDIUM)
            startActivity(intent)
        }
        binding.btnHard.setOnClickListener {
            intent.putExtra(LEVEL_KEY , HARD)
            startActivity(intent)
        }
        binding.btnExpert.setOnClickListener {
            intent.putExtra(LEVEL_KEY , EXPERT)
            startActivity(intent)
        }

    }
}