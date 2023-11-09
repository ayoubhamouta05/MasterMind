package com.example.myshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myshop.R
import com.example.myshop.databinding.ActivitySelectLevelBinding
import com.example.myshop.databinding.ExplainGameBottomSheetLayoutBinding
import com.example.myshop.databinding.SelectColorBottomSheetLayoutBinding
import com.example.myshop.util.Constants.EASY
import com.example.myshop.util.Constants.EXPERT
import com.example.myshop.util.Constants.HARD
import com.example.myshop.util.Constants.LEVEL_KEY
import com.example.myshop.util.Constants.MEDIUM
import com.google.android.material.bottomsheet.BottomSheetDialog

class SelectLevelActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySelectLevelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectLevel()
        binding.howToPlayTv.setOnClickListener {
            showBottomSheet()
        }

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

    private fun showBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetBinding = ExplainGameBottomSheetLayoutBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

//        val behavior = BottomSheetBehavior.from(bottomSheetBinding.root.parent as View)
//        behavior.isDraggable = true
        bottomSheetDialog.show()

    }
}