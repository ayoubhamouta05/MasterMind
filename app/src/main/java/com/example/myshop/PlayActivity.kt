package com.example.myshop

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.adapter.CheckColorsAdapter
import com.example.myshop.adapter.TryColorsAdapter
import com.example.myshop.databinding.ActivityPlayBinding
import com.example.myshop.databinding.BottomSheetLayoutBinding
import com.example.myshop.databinding.CongratulationDialogBinding
import com.example.myshop.databinding.FailureDialogBinding
import com.example.myshop.databinding.RestartGameDialogBinding
import com.example.myshop.models.CheckColorsData
import com.example.myshop.models.TryColorsData
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val COLORS = arrayListOf("red", "white", "black", "green", "grey", "blue", "yellow", "purple")

class PlayActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayBinding
    lateinit var checkColorsAdapter: CheckColorsAdapter
    lateinit var tryColorsAdapter: TryColorsAdapter
    var checkColorsList = arrayListOf<CheckColorsData>()
    var tryColorsList = arrayListOf<TryColorsData>()

    var resultCheckColor = arrayListOf("", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        play(fillTheTable(COLORS))

        binding.restartBtn.setOnClickListener {
            showRestartDialog()
        }

        controlRVs()


    }

    private fun controlRVs(){
        binding.rvTryColors.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                // Get the vertical scroll offset of the first RecyclerView
                val scrollY = recyclerView.computeVerticalScrollOffset()

                // Scroll the second RecyclerView to the same position
                binding.rvCheckColors.scrollBy(0, dy)
            }
        })
        binding.rvCheckColors.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                // Get the vertical scroll offset of the first RecyclerView
                val scrollY = recyclerView.computeVerticalScrollOffset()

                // Scroll the second RecyclerView to the same position
                binding.rvTryColors.scrollBy(0, dy)
            }
        })
    }

    private fun showRestartDialog(){
        val dialog = Dialog(this)
        val dialogBinding = RestartGameDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(
            resources.getDrawable(
                R.drawable._button_background,
                null
            )
        )

        dialogBinding.tryAgainBtn.setOnClickListener {
            playAgain()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun play(colors: ArrayList<String>) {
        Log.d("PlayActivityy", colors.toString())

        setupCheckRv()
        setupTryRv()
        showBottomSheet()

        binding.checkColorBtn.setOnClickListener {

            if (binding.checkColorBtn.error != null) {
                binding.checkColorBtn.error = null // cancel the error state
            }

            var colorsInTheSamePlace = 0

            val checkColorsArray = mutableListOf<Int>() // set the data of the check colors

            if (checkIfColorsChosenAreNotEmpty()) {
                // check Colors
                for (i in resultCheckColor.indices) {
                    if (resultCheckColor[i] == colors[i]) {
                        colorsInTheSamePlace += 1
                        checkColorsArray.add(2) // to set the red color == mean in the right place
                    } else if (colors.contains(resultCheckColor[i])) {
                        checkColorsArray.add(1) // to set the white color == mean is not in the right place
                    } else {
                        checkColorsArray.add(0)
                    }
                }

                setUserTrying(checkColorsArray)
                binding.opportunity.text = "you have ${10 - tryColorsAdapter.differ.currentList.size} opportunity"

                // check if is matched
                if (colorsInTheSamePlace == colors.size) {
                    /** the player win */
                    showWinDialog(colors)
                } else if (tryColorsAdapter.differ.currentList.size == 10) {
                    showFailureDialog(colors)
                }

            } else {
                /** show the error that he must set all colors*/
                val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                binding.checkColorBtn.apply {
                    error = "Missing Colors"
                    vibrator.vibrate(100)
                    Toast.makeText(this@PlayActivity, "Missing Colors", Toast.LENGTH_SHORT).show()
                }

            }

        }

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


    private fun showFailureDialog(colors: ArrayList<String>) {
        val dialog = Dialog(this)
        val dialogBinding = FailureDialogBinding.inflate(layoutInflater)
        dialog.setCancelable(false)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(
            resources.getDrawable(
                R.drawable._button_background,
                null
            )
        )
        dialogBinding.apply {
            Item1.setBackgroundColor(resources.getColor(selectColor(colors[0]),null))
            Item2.setBackgroundColor(resources.getColor(selectColor(colors[1]),null))
            Item3.setBackgroundColor(resources.getColor(selectColor(colors[2]),null))
            Item4.setBackgroundColor(resources.getColor(selectColor(colors[3]),null))
            Item5.setBackgroundColor(resources.getColor(selectColor(colors[4]),null))
        }


        dialogBinding.tryAgainBtn.setOnClickListener {
            playAgain()
            dialog.dismiss()
        }

        dialogBinding.exitGameBtn.setOnClickListener {
            finishAffinity()
            dialog.dismiss()
        }


        dialog.show()
    }

    private fun showWinDialog(colors: ArrayList<String>) {

        val dialog = Dialog(this)
        val dialogBinding = CongratulationDialogBinding.inflate(layoutInflater)
        dialog.setCancelable(false)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(
            resources.getDrawable(
                R.drawable._button_background,
                null
            )
        )
        dialogBinding.numberTriesTv.text = "With ${tryColorsAdapter.differ.currentList.size} Tries"

        dialogBinding.apply {
            Item1.setBackgroundColor(resources.getColor(selectColor(colors[0]),null))
            Item2.setBackgroundColor(resources.getColor(selectColor(colors[1]),null))
            Item3.setBackgroundColor(resources.getColor(selectColor(colors[2]),null))
            Item4.setBackgroundColor(resources.getColor(selectColor(colors[3]),null))
            Item5.setBackgroundColor(resources.getColor(selectColor(colors[4]),null))
        }

        dialogBinding.tryAgainBtn.setOnClickListener {
            playAgain()
            dialog.dismiss()
        }

        dialogBinding.exitGameBtn.setOnClickListener {
            finishAffinity()
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun playAgain() {
        play(fillTheTable(COLORS))
        setAllItemsEmpty()
        checkColorsList = arrayListOf()
        tryColorsList = arrayListOf()
        tryColorsAdapter.differ.submitList(tryColorsList)
        checkColorsAdapter.differ.submitList(checkColorsList)
        binding.opportunity.text = "you have 10 opportunity"
    }

    /** check the colors action */
    private fun setUserTrying(checkColorsArray: MutableList<Int>) {
        val newCheckColorsArray = removeDuplicatedColors(resultCheckColor, checkColorsArray)

        checkColorsList.add(
            CheckColorsData(
                newCheckColorsArray[0],
                newCheckColorsArray[1],
                newCheckColorsArray[2],
                newCheckColorsArray[3],
                newCheckColorsArray[4],
            )
        )
        checkColorsAdapter.notifyDataSetChanged()

        tryColorsList.add(
            TryColorsData(
                resultCheckColor[0],
                resultCheckColor[1],
                resultCheckColor[2],
                resultCheckColor[3],
                resultCheckColor[4],
            )
        )
        tryColorsAdapter.notifyDataSetChanged()

        setAllItemsEmpty()
    }

    /** when the user check the colors all the items return to the normal state */
    private fun setAllItemsEmpty() {
        binding.item1.background = resources.getDrawable(R.drawable._items_background, null)
        binding.item2.background = resources.getDrawable(R.drawable._items_background, null)
        binding.item3.background = resources.getDrawable(R.drawable._items_background, null)
        binding.item4.background = resources.getDrawable(R.drawable._items_background, null)
        binding.item5.background = resources.getDrawable(R.drawable._items_background, null)
        resultCheckColor = arrayListOf("", "", "", "", "")
    }

    /** retreive the place and existence of colors */
    fun removeDuplicatedColors(
        colorList: ArrayList<String>,
        checkColorsArray: MutableList<Int>
    ): MutableList<Int> {
        for (i in colorList.indices) {
            for (j in i + 1 until colorList.size) {
                if (colorList[i] == colorList[j]) {
                    checkColorsArray[j] =
                        0 // set value 0 == mean the color does not exist or duplicated
                }
            }
        }
        return checkColorsArray.sorted().reversed().toMutableList()
    }

    fun checkIfColorsChosenAreNotEmpty(): Boolean {
        for (i in resultCheckColor) {
            if (i.isEmpty()) {
                return false
            }
        }
        return true
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

    private fun showBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetBinding = BottomSheetLayoutBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        for (i in 0 until binding.originalColorsLayout.childCount - 1) {
            val childView = binding.originalColorsLayout.getChildAt(i)
            childView.setOnClickListener {

                bottomSheetDialog.show()
                setupBottomSheetClickListener(
                    bottomSheetDialog,
                    bottomSheetBinding,
                    getItemSelected(i),
                    i
                )

            }
        }
    }

    private fun getItemSelected(i: Int): ImageView {
        return when (i) {
            0 -> {
                findViewById(R.id.item1)
            }

            1 -> {
                findViewById(R.id.item2)
            }

            2 -> {
                findViewById(R.id.item3)
            }

            3 -> {
                findViewById(R.id.item4)
            }

            else -> {
                findViewById(R.id.item5)
            }
        }
    }

    private fun setupBottomSheetClickListener(
        bottomSheetDialog: BottomSheetDialog,
        bottomSheetBinding: BottomSheetLayoutBinding,
        imageView: View,
        i: Int
    ) {
        bottomSheetBinding.black.setOnClickListener {
            imageView.setBackgroundColor(resources.getColor(R.color.black, null))
            bottomSheetDialog.dismiss()
            resultCheckColor[i] = "black"
        }
        bottomSheetBinding.white.setOnClickListener {
            imageView.setBackgroundColor(resources.getColor(R.color.white, null))
            bottomSheetDialog.dismiss()
            resultCheckColor[i] = "white"
        }
        bottomSheetBinding.green.setOnClickListener {
            imageView.setBackgroundColor(resources.getColor(R.color.green, null))
            bottomSheetDialog.dismiss()
            resultCheckColor[i] = "green"
        }
        bottomSheetBinding.red.setOnClickListener {
            imageView.setBackgroundColor(resources.getColor(R.color.red, null))
            bottomSheetDialog.dismiss()
            resultCheckColor[i] = "red"
        }
        bottomSheetBinding.yellow.setOnClickListener {
            imageView.setBackgroundColor(resources.getColor(R.color.yellow, null))
            bottomSheetDialog.dismiss()
            resultCheckColor[i] = "yellow"
        }
        bottomSheetBinding.grey.setOnClickListener {
            imageView.setBackgroundColor(resources.getColor(R.color.grey, null))
            bottomSheetDialog.dismiss()
            resultCheckColor[i] = "grey"
        }
        bottomSheetBinding.blue.setOnClickListener {
            imageView.setBackgroundColor(resources.getColor(R.color.blue, null))
            bottomSheetDialog.dismiss()
            resultCheckColor[i] = "blue"
        }
        bottomSheetBinding.purple.setOnClickListener {
            imageView.setBackgroundColor(resources.getColor(R.color.purple, null))
            bottomSheetDialog.dismiss()
            resultCheckColor[i] = "purple"
        }
    }

    private fun setupCheckRv() {
        checkColorsAdapter = CheckColorsAdapter()
        binding.rvCheckColors.apply {
            var mlayoutManager = LinearLayoutManager(this@PlayActivity)
            mlayoutManager.reverseLayout = true
            layoutManager = mlayoutManager
            adapter = checkColorsAdapter
            checkColorsAdapter.differ.submitList(checkColorsList)

        }
    }

    private fun setupTryRv() {
        tryColorsAdapter = TryColorsAdapter()
        binding.rvTryColors.apply {
            var mlayoutManager = LinearLayoutManager(this@PlayActivity)
            mlayoutManager.reverseLayout = true
            layoutManager = mlayoutManager
            adapter = tryColorsAdapter
            tryColorsAdapter.differ.submitList(tryColorsList)

        }
    }
}