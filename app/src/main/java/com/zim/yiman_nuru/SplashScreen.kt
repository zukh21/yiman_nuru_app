package com.zim.yiman_nuru

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.widget.SwitchCompat

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    lateinit var switch: SwitchCompat
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        Night mode switcher start
        val view: View = layoutInflater.inflate(R.layout.activity_main, null)
        val splash_screen_layout: RelativeLayout = findViewById(R.id.splash_screen_layout)
        switch = view.findViewById(R.id.switch_btn_night_mode)
//        when (AppCompatDelegate.getDefaultNightMode()){
//            MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
//        }


//        проверить ночной режим по умолчанию START
//        if (AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_NO ||
//            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM ||
//            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY){
//            switch.isChecked = false // отключает кнопку Switch когда яркий режим телефона
//        }else if (
//            AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES
//        ){
//            switch.isChecked = true // включает кнопку Switch когда тёмный режим телефона
//        }

        //        проверить ночной режим по умолчанию END

//        val bismilla: MediaPlayer = MediaPlayer.create(this, R.raw.bismillah)
//        bismilla.setVolume(0.05f, 0.05f)
//        bismilla.start()
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)

    }
}