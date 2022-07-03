package com.zim.yiman_nuru

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.zim.yiman_nuru.databinding.ActivityZikirBinding

class Zikir : AppCompatActivity() {
    lateinit var binding: ActivityZikirBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZikirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Вверные меню старт
        binding.myTelegramBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://t.me/zukh_kamchybekov"))
            startActivity(intent)
        }
        binding.btnBackNavTop.setOnClickListener {
            finish()
        }
    }
}