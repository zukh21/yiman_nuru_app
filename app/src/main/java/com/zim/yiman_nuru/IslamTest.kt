package com.zim.yiman_nuru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.MobileAds
import com.zim.yiman_nuru.databinding.ActivityIslamTestBinding
import com.zim.yiman_nuru.levels.*

open class IslamTest : AppCompatActivity() {
    private var backPressedTime = 0L

    lateinit var binding: ActivityIslamTestBinding
    val testDBManager = TestDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIslamTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        binding.buttonBackGame.setOnClickListener {
//            super.onBackPressed()
//            binding.buttonBackGame.setBackgroundResource(R.drawable.round_bg)
//        }

//        кнопки уровня START

        testDBManager.openDB()
        testDBManager.insertToDB(1)
        val dataList = testDBManager.readDBData()



        buttonsGroup() // кнопки уровня

//        кнопки уровня END

        AdsYandex().ads(binding.adView)     // Рекламный блок

    }


    fun buttonsGroup(){
        levelsOnClick(binding.btnLevelOne, 1, FirstLevelActivity())
        levelsOnClick(binding.btnLevelTwo, 2, SecondLevelActivity())
        levelsOnClick(binding.btnLevelThree, 3, ThirdLevelActivity())
        levelsOnClick(binding.btnLevelFour, 4, FourthLevelActivity())
        levelsOnClick(binding.btnLevelFife, 5, FifthLevelActivity())
        levelsOnClick(binding.btnLevelSix, 6, SixthLevelActivity())
        levelsOnClick(binding.btnLevelSeven, 7, SeventhLevelActivity())
        levelsOnClick(binding.btnLevelEight, 8, EighthsLevelActivity())
        levelsOnClick(binding.btnLevelNine, 9, NinthLevelActivity())
        levelsOnClick(binding.btnLevelTen, 10, TenthLevelActivity())
        levelsOnClick(binding.btnLevelEleven, 11, null)
        levelsOnClick(binding.btnLevelTwelve, 12, null)
        levelsOnClick(binding.btnLevelThirteen, 13, null)
        levelsOnClick(binding.btnLevelFourteen, 14, null)
        levelsOnClick(binding.btnLevelFifteen, 15, null)
        levelsOnClick(binding.btnLevelSixteen, 16, null)
    }

    fun levelsOnClick(clickedButton: TextView, clickedButtonNumber: Int, activity: Any?){
        val dataList = testDBManager.readDBData()

            clickedButton.setOnClickListener {
                if (clickedButtonNumber in dataList) {
                    if (activity != null) {
                        startActivity(Intent(this, activity.javaClass))
                    }
                    finish()
                }else Toast.makeText(this, "Мурдагы деңгээлди аяктаңыз!", Toast.LENGTH_SHORT).show()
            }
        if (clickedButtonNumber in dataList){
            setStyleToLevelButtons(clickedButton, clickedButtonNumber.toString())
        }
    }

     fun setStyleToLevelButtons(button: TextView, buttonNumber: String){
        button.text = buttonNumber
        button.textSize = 20F
        button.setCompoundDrawablesRelative(null, null, null, null)
        button.setBackgroundResource(R.drawable.levels_bg_after_click)
    }

    override fun onDestroy() {
        super.onDestroy()
        testDBManager.closeDB()
    }

    override fun onBackPressed() {
            super.onBackPressed()
    }
}