package com.zim.yiman_nuru

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.yandex.mobile.ads.common.MobileAds
import com.zim.yiman_nuru.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var switch: SwitchCompat
    lateinit var text_modes: TextView
    lateinit var text_yiman_nuru: TextView
    lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var handler: Handler
    private val YANDEX_MOBILE_ADS_TAG = "YandexMobileAds"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this
        ) { Log.d(YANDEX_MOBILE_ADS_TAG, "SDK initialized") }


        drawerLayout = findViewById(R.id.drawer)
        val navView: NavigationView = findViewById(R.id.navigation_view)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE) // Запрет на скриншот


//        Запуск инстаграма START

        val logo_bottom_in_menu: ImageView = findViewById(R.id.logo_bottom_in_menu)
        logo_bottom_in_menu.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/kg.zim.company/"))
            startActivity(intent)
        }

//        Запуск инстаграма END
//
//        Запуск Telegram START

        val share_btn: LinearLayout = findViewById(R.id.share_btn)
        share_btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.t.me/yiman_nuru_zim"))
            startActivity(intent)
        }

//        Запуск Telegram END



        // запуск вверхнего меню

        val menu_btn: ImageView = findViewById(R.id.btn_menu)
        menu_btn.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // конец вверхнего меню

        // запуск бокового меню

        navView.setNavigationItemSelectedListener {
            when (it.itemId){
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
                R.id.id_privacy_policy ->{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://zukh21.github.io/privacy_policy_yiman_nuru.github.io/"))
                    startActivity(intent)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true

        }

        // конец бокового меню

//        Night mode switcher start

        switch = findViewById(R.id.switch_btn_night_mode)
        text_modes = findViewById(R.id.text_modes)
         text_yiman_nuru = findViewById(R.id.text_yiman_nuru)
        val banner_img: ImageView = findViewById(R.id.banner_img)

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
            banner_img.setImageResource(R.drawable.slide_1)
        }else if (text_modes.text == "light"){
            switch.isChecked = false
            banner_img.setImageResource(R.drawable.slide_5)
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
//        onClickHorizontalActivity() // Горизонтальные кнопки (горизонтальное меню)

//        Bottom navigation START

        binding.bottomNavigationViewId.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_menu_btn_testG -> {
                    val intent = Intent(this, SplashScreenForTestGame::class.java)
                    startActivity(intent)
                }
                R.id.bottom_menu_btn_tasbih -> {
                    val intent = Intent(this, TasbihActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }


//        Bottom navigation END








    }

    //        переключить ночной режим END


    fun mainFragment(){
        val text_banner: TextView = findViewById(R.id.text_banner)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, MainFragment.newInstance())
            .commit()
        text_banner.text = "Yiman nuru"
    }


}