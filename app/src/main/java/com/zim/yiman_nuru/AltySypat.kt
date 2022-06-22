package com.zim.yiman_nuru

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class AltySypat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alty_sypat)
        val my_telegram_btn: TextView = findViewById(R.id.my_telegram_btn)
        my_telegram_btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://t.me/zukh_kamchybekov"))
            startActivity(intent)
        }
        // Вверные меню старт
//        val menu_btn: ImageView = findViewById(R.id.menu_btn)
//        menu_btn.setOnClickListener {
//            Toast.makeText(this, "Азырынча меню даяр эмес!", Toast.LENGTH_LONG).show()
//        }
//        back_btn.setOnClickListener {
//            finish()
//        }
        // Вверные меню енд
    }
}