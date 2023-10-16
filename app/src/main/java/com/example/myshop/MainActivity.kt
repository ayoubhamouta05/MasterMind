package com.example.myshop

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.myshop.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val COLORS = arrayListOf("red", "white", "black", "green", "grey", "blue", "yellow", "purple")
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playGame(fillTheTable(COLORS))

    }

    fun selectColor(color: String): Int {
        return when (color) {
            "red" -> {
                R.color.red
            }

            "black" -> {
                R.color.black
            }

            "white" -> {
                R.color.white
            }

            "purple" -> {
                R.color.purple
            }

            "yellow" -> {
                R.color.yellow
            }

            "grey" -> {
                R.color.grey
            }

            "blue" -> {
                R.color.blue
            }

            else -> {
                R.color.green
            }
        }
    }

    private fun showAlertDialog(result : Double) {
        val builder = AlertDialog.Builder(this)

        // Set the title for the AlertDialog
        builder.setTitle("Congratulations ")
        builder.setMessage("you got : $result points")
        builder.setCancelable(false)

        builder.setPositiveButton("Try Again") { dialog,_ ->
            playGame(fillTheTable(COLORS))
            resetView()
            dialog.dismiss()
        }

        builder.setNegativeButton("Exit") { dialog, _ ->
            finish()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    fun resetView(){
        binding.apply {
            colorNumber.setText("")
            colorName.setText("")
            item1.setBackgroundColor(resources.getColor(R.color.nullColor,null))
            item2.setBackgroundColor(resources.getColor(R.color.nullColor,null))
            item3.setBackgroundColor(resources.getColor(R.color.nullColor,null))
            item4.setBackgroundColor(resources.getColor(R.color.nullColor,null))
            item5.setBackgroundColor(resources.getColor(R.color.nullColor,null))
            opportunity.text = "you have 10 opportunity"

        }
    }

    fun playGame(colorsToGuess: ArrayList<String>) {

        var result = 0.0

        val colorsSelected = arrayListOf("", "", "", "", "")

        var i = 0

        binding.checkColor.setOnClickListener {
            hideKeyboard()
            if (i < 10) {
                if (binding.colorName.text.toString().isEmpty()) {
                    binding.colorName.error = "Select a Color"
                } else if (binding.colorNumber.text.toString().isEmpty() ) {
                    binding.colorNumber.error = "Select a Number"
                } else {
                    val choseColor = binding.colorName.text.toString()
                    val choseIndex = binding.colorNumber.text.toString().toInt()
                    if (!COLORS.contains(choseColor) || choseIndex > 5) {
                        if (!COLORS.contains(choseColor))
                            binding.colorName.error = "this color is not available"
                        if (choseIndex > 5)
                            binding.colorNumber.error = "Select a Number"
                    } else {

                        if (!colorsSelected.contains(choseColor)) {  // check if color is already chosen
                            if (colorsToGuess.contains(choseColor.lowercase().trim())) {
                                colorsSelected[choseIndex - 1] = choseColor
                                if (colorsToGuess[choseIndex - 1] == choseColor) {
                                    Toast.makeText(
                                        this,
                                        "+1 ... Color and Index are Correct",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    setTheColor(choseColor, choseIndex)
                                    binding.colorName.setText("")
                                    binding.colorNumber.setText("")
                                    //println("+1 ... Color and Index are Correct \n")
                                    result += 1
                                } else {
                                    Toast.makeText(
                                        this,
                                        "+0.5 ... The Index is Not Correct",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    binding.colorNumber.setText("")
                                    //println("+0.5 ... The Index is Not Correct\n")
                                    result += 0.5
                                }
                            } else {
                                Toast.makeText(
                                    this,
                                    "+0 ... the Color is not correct",
                                    Toast.LENGTH_SHORT
                                ).show()
                                binding.colorName.setText("")
                                binding.colorNumber.setText("")

                            }
                        } else {
                            if (colorsToGuess[choseIndex - 1] == choseColor && colorsSelected[choseIndex - 1] != choseColor) {
                                Toast.makeText(
                                    this,
                                    "+0.5 ... you found the correct Index",
                                    Toast.LENGTH_SHORT
                                ).show()

                                colorsSelected[choseIndex - 1] = choseColor
                                setTheColor(choseColor, choseIndex)
                                result += 0.5
                                binding.colorName.setText("")
                                binding.colorNumber.setText("")
                            } else if (colorsToGuess[choseIndex - 1] == choseColor && colorsSelected[choseIndex - 1] == choseColor) {
                                Toast.makeText(
                                    this,
                                    "You Already found this color please try another one",
                                    Toast.LENGTH_SHORT
                                ).show()
                                binding.colorName.setText("")
                                binding.colorNumber.setText("")
                                i--
                            } else {
                                Toast.makeText(
                                    this,
                                    "+0 ... the Index is not correct",
                                    Toast.LENGTH_SHORT
                                ).show()

                                binding.colorNumber.setText("")
                            }
                        }
                        if (colorsSelected == colorsToGuess){
                            showAlertDialog(result)
                        }
                        i++
                    }
                    binding.opportunity.text = "you have ${10-i} opportunity"

                }
                if (i>=10){
                    showAlertDialog(result)
                }
            }
        }

    }


    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.colorName.windowToken,0)
        imm.hideSoftInputFromWindow(binding.colorNumber.windowToken,0)
    }

    fun setTheColor(choseColor: String, choseIndex: Int) {
        when (choseIndex) {
            1 -> {
                binding.item1.setBackgroundColor(resources.getColor(selectColor(choseColor), null))
            }

            2 -> {
                binding.item2.setBackgroundColor(resources.getColor(selectColor(choseColor), null))
            }

            3 -> {
                binding.item3.setBackgroundColor(resources.getColor(selectColor(choseColor), null))
            }

            4 -> {
                binding.item4.setBackgroundColor(resources.getColor(selectColor(choseColor), null))
            }

            5 -> {
                binding.item5.setBackgroundColor(resources.getColor(selectColor(choseColor), null))
            }
        }

    }

    fun fillTheTable(colors: ArrayList<String>): ArrayList<String> {
        val colorsToGuess = arrayListOf<String>()
        val colorsSelected = arrayListOf<Int>()

        var i = 0
        while (i < 5) {
            val rand = (0..7).random()
            if (!colorsSelected.contains(rand)) {
                colorsToGuess.add(colors[rand])
                colorsSelected.add(rand)
                i++
            }
        }
        return colorsToGuess
    }



}