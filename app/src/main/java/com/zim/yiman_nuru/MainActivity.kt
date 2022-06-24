package com.zim.yiman_nuru

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.zim.yiman_nuru.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var switch: SwitchCompat

    lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    @SuppressLint("SwitchIntDef")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = findViewById(R.id.drawer)
        val navView: NavigationView = findViewById(R.id.navigation_view)



        // запуск ввернего меню

        val menu_btn: ImageView = findViewById(R.id.btn_menu)
        menu_btn.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // конец вверхнего меню

        // запуск бокового меню

        navView.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.id_home_activity -> {
                    Toast.makeText(this, "zukh", Toast.LENGTH_LONG).show()
                }
                R.id.id_about_us_activity -> {
                    val intent = Intent(this, AboutUs::class.java)
                    startActivity(intent)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true

        }

        // конец бокового меню

        mainFragment() // фрагмент главного экрана
        onClickHorizontalActivity() // Горизонтальные кнопки (горизонтальное меню)

//        Night mode switcher start

        switch = findViewById(R.id.switch_btn_night_mode)
//        when (AppCompatDelegate.getDefaultNightMode()){
//            MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
//        }

//        проверить ночной режим по умолчанию START
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO ||
                AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM ||
                AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY){
            switch.isChecked = false // отключает кнопку Switch когда яркий режим телефона
        }else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED ||
            AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES
        ){
            switch.isChecked = true // включает кнопку Switch когда тёмный режим телефона
        }

        //        проверить ночной режим по умолчанию END


//        переключить ночной режим START

        switch.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                onRestart()
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                onRestart()
            }
        }

    }

    //        переключить ночной режим END


    fun mainFragment(){
        val text_yiman_nuru: TextView = findViewById(R.id.text_yiman_nuru)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, MainFragment.newInstance())
            .commit()
        text_yiman_nuru.text = "Yiman nuru"
    }
//
    fun onClickHorizontalActivity() {
    val text_yiman_nuru: TextView = findViewById(R.id.text_yiman_nuru)
    val horizontal_btn_tasbih = findViewById<Button>(R.id.horizontal_btn_tasbih)
    val horizontal_btn_zikir = findViewById<Button>(R.id.horizontal_btn_zikir)
    val horizontal_btn_masnun_duba = findViewById<Button>(R.id.horizontal_btn_masnun_duba)
    val horizontal_btn_alty_sypat = findViewById<Button>(R.id.horizontal_btn_alty_sypat)
    val horizontal_btn_allahtyn_ysymdary = findViewById<Button>(R.id.horizontal_btn_allahtyn_ysymdary)
    val horizontal_btn_main = findViewById<Button>(R.id.horizontal_btn_main)


    horizontal_btn_main.setOnClickListener {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, MainFragment.newInstance())
            .commit()
        text_yiman_nuru.text = "Yiman nuru"
    }

    horizontal_btn_tasbih.setOnClickListener {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, TasbihFragment.newInstance())
            .commit()
        text_yiman_nuru.text = "Tasbih"
    }
    horizontal_btn_zikir.setOnClickListener {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, ZikirTypesFragment.newInstance())
            .commit()
        text_yiman_nuru.text = "Zikirdin turloru"
    }
    horizontal_btn_masnun_duba.setOnClickListener {
        startActivity(Intent(this, MasnunDubalar::class.java))
    }
    horizontal_btn_alty_sypat.setOnClickListener {
        startActivity(Intent(this, AltySypat::class.java))
    }
    horizontal_btn_allahtyn_ysymdary.setOnClickListener {
        startActivity(Intent(this, AllahtynYsymdary::class.java))
    }


}


}