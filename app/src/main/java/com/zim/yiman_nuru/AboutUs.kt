package com.zim.yiman_nuru

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDelegate

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        val my_telegram_btn: LinearLayout = findViewById(R.id.my_telegram_btn)
        my_telegram_btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/YimanNuruSupport"))
            startActivity(intent)
        }
    }
}