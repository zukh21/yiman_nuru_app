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
import com.zim.yiman_nuru.levels.SecondLevelActivity
import com.zim.yiman_nuru.levels.FirstLevelActivity

class IslamTest : AppCompatActivity() {
    private var backPressedTime = 0L
    private val YANDEX_MOBILE_ADS_TAG = "YandexMobileAds"
    lateinit var binding: ActivityIslamTestBinding
    val testDBManager = TestDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIslamTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this
        ) { Log.d(YANDEX_MOBILE_ADS_TAG, "SDK initialized") }
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        binding.buttonBackGame.setOnClickListener {
//            super.onBackPressed()
//            binding.buttonBackGame.setBackgroundResource(R.drawable.round_bg)
//        }

//        кнопки уровня START

        testDBManager.openDB()
        testDBManager.insertToDB(1)
        val dataList = testDBManager.readDBData()


        levelsOnClick(binding.btnLevelOne, 1, FirstLevelActivity())
        levelsOnClick(binding.btnLevelTwo, 2, SecondLevelActivity())
        levelsOnClick(binding.btnLevelThree, 3, null)


//        кнопки уровня END

        val banner = findViewById<BannerAdView>(R.id.adView)
        banner.setAdUnitId("R-M-DEMO-320x100")
        banner.setAdSize(AdSize.BANNER_320x100)

        val adRequest = AdRequest.Builder().build()
        banner.loadAd(adRequest)

    }

    fun levelsOnClick(clickedButton: TextView, clickedButtonNumber: Int, activity: Any?){
        val dataList = testDBManager.readDBData()

            clickedButton.setOnClickListener {
                if (clickedButtonNumber in dataList) {
                    if (activity != null) {
                        startActivity(Intent(this, activity.javaClass))
                    }
                    finish()
                }else Toast.makeText(this, "text", Toast.LENGTH_SHORT).show()
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