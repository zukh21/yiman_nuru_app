package com.zim.yiman_nuru

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.zim.yiman_nuru.databinding.ActivitySplashScreenForTestGameBinding

class SplashScreenForTestGame : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenForTestGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenForTestGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding.buttonStartGame.setOnClickListener {
            binding.buttonStartGame.setTextColor(Color.GREEN)
            binding.buttonStartGame.setBackgroundResource(R.drawable.bg_button_outline_green)
            startActivity(Intent(this, IslamTest::class.java))
            finish()
        }
    }
}
