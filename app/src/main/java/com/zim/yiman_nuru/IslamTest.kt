package com.zim.yiman_nuru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.zim.yiman_nuru.databinding.ActivityIslamTestBinding
import com.zim.yiman_nuru.levels.SecondLevelActivity
import com.zim.yiman_nuru.levels.FirstLevelActivity

class IslamTest : AppCompatActivity() {
    private var backPressedTime = 0L
    lateinit var binding: ActivityIslamTestBinding
    val testDBManager = TestDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIslamTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)


        binding.buttonBackGame.setOnClickListener {
            if (backPressedTime + 2000 > System.currentTimeMillis()){
                super.onBackPressed()
            }else{
                Toast.makeText(baseContext, "Чыгуу үчүн, дагы бир жолу басыңыз", Toast.LENGTH_SHORT).show()
            }
            backPressedTime = System.currentTimeMillis()
        }

//        кнопки уровня START

        testDBManager.openDB()
        testDBManager.insertToDB(1)
        val dataList = testDBManager.readDBData()


        levelsOnClick(binding.btnLevelOne, 1, FirstLevelActivity())
        levelsOnClick(binding.btnLevelTwo, 2, SecondLevelActivity())


//        кнопки уровня END

    }

    fun levelsOnClick(clickedButton: TextView, clickedButtonNumber: Int, activity: Any){
        val dataList = testDBManager.readDBData()
        if (clickedButtonNumber in dataList){
            clickedButton.setOnClickListener {
                if (clickedButtonNumber in dataList) {
                    startActivity(Intent(this, activity.javaClass))
                    finish()
                }else Toast.makeText(this, "text", Toast.LENGTH_SHORT).show()
            }
            setStyleToLevelButtons(clickedButton, clickedButtonNumber.toString())
        }
    }

     fun setStyleToLevelButtons(button: TextView, buttonNumber: String){
        button.text = buttonNumber
        button.width = 170
        button.height = 170
        button.textSize = 22F
        button.setCompoundDrawablesRelative(null, null, null, null)
        button.setBackgroundResource(R.drawable.levels_bg_after_click)
    }

    override fun onDestroy() {
        super.onDestroy()
        testDBManager.closeDB()
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
            return
        }else{
            Toast.makeText(baseContext, "Чыгуу үчүн, дагы бир жолу басыңыз", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}