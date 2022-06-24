package com.zim.yiman_nuru

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.zim.yiman_nuru.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var switch: SwitchCompat
    lateinit var text_modes: TextView

    lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    @SuppressLint("SwitchIntDef", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = findViewById(R.id.drawer)
        val navView: NavigationView = findViewById(R.id.navigation_view)



        // запуск вверхнего меню

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
                R.id.id_contacts -> {

                    val view: View = layoutInflater.inflate(R.layout.activity_contacts_bottom_sheet, null)
                    val dialog = BottomSheetDialog(this)
                    dialog.setContentView(view)
                    val close_sheet: ImageView = view.findViewById(R.id.close_sheet)
                    dialog.show()
                    close_sheet.setOnClickListener {
                        dialog.dismiss()
                    }
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true

        }

        // конец бокового меню

//        Night mode switcher start

        switch = findViewById(R.id.switch_btn_night_mode)
        text_modes = findViewById(R.id.text_modes)
        val text_yiman_nuru: TextView = findViewById(R.id.text_yiman_nuru)

// проверка цвета фона текста START

        val cd = text_yiman_nuru.background as ColorDrawable
        val color = cd.color
        val alpha = cd.alpha
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)

// проверка цвета фона текста END

//        изменение текста Text_Modes START
        if (alpha == 255 && red == 38 && green == 60 && blue == 81){
            text_modes.text = "night"
        }else if(alpha == 255 && red == 1 && green == 88 && blue == 54){
            text_modes.text = "light"
        }
//        изменение текста Text_Modes END

//        проверка Switcher START
        if (text_modes.text == "night"){
            switch.isChecked = true
        }else if (text_modes.text == "light"){
            switch.isChecked = false
        }

        //        проверка Switcher END

//        переключить ночной режим START

        switch.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }

        mainFragment() // фрагмент главного экрана
        onClickHorizontalActivity() // Горизонтальные кнопки (горизонтальное меню)

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