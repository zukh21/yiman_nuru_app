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

class Zikir : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zikir)
        val my_telegram_btn: LinearLayout = findViewById(R.id.my_telegram_btn)
        // Вверные меню старт
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Yiman Nuru"
        supportActionBar?.subtitle = "MyApp"
        // Вверные меню енд
        my_telegram_btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://t.me/zukh_kamchybekov"))
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return true
    }
}