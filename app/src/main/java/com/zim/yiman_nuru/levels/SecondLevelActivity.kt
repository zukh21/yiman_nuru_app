package com.zim.yiman_nuru.levels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zim.yiman_nuru.IslamTest
import com.zim.yiman_nuru.R
import com.zim.yiman_nuru.databinding.ActivitySecondLevelBinding

class SecondLevelActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondLevelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, IslamTest::class.java))
    }
}