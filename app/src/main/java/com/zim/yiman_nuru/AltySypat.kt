package com.zim.yiman_nuru

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.zim.yiman_nuru.databinding.ActivityAltySypatBinding

class AltySypat : AppCompatActivity() {
    lateinit var binding: ActivityAltySypatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltySypatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.myTelegramBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://t.me/zukh_kamchybekov"))
            startActivity(intent)
        }
        binding.btnBackNavTop.setOnClickListener {
            finish()
        }
    }
}