package com.zim.yiman_nuru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.zim.yiman_nuru.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = findViewById(R.id.drawer)
        val navView: NavigationView = findViewById(R.id.navigation_view)

        // Вверхные меню старт
        val menu_btn: ImageView = findViewById(R.id.btn_menu)
        menu_btn.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        // Вверхные меню енд

        // боковое меню старт

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

        // боковое меню енд

        mainFragment()
        buttons()
        onClickHorizontalActivity()

    }

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


    fun buttons() {

//        val btnIslam_test: CardView = findViewById(R.id.btn_islam_test)
//        val btnMasnunDubalar: CardView = findViewById(R.id.btn_masnun_dubalar)
//        val btnTasbih: CardView = findViewById(R.id.btn_tasbih)
//        val btn_alty_sypat: CardView = findViewById(R.id.btn_alty_sypat)
//        val btn_zikir: CardView = findViewById(R.id.btn_zikir)
//        val btn_allanyn_ysymdary: CardView = findViewById(R.id.btn_allanyn_ysymdary)
//
//        btn_allanyn_ysymdary.setOnClickListener {
//            Toast.makeText(this, "Азыркы учурда бул баракча даяр эмес!", Toast.LENGTH_LONG).show()
//        }
//
//        btn_zikir.setOnClickListener {
//            val intent = Intent(this, Zikir::class.java)
//            startActivity(intent)
//        }
////        val btn_namaz: CardView = findViewById(R.id.btn_namaz)
//
////        btn_namaz.setOnClickListener {
////            Toast.makeText(this, "Азыркы учурда бул баракча түзүүчүлөр тараптан иштелип жатат кудай буюрса тиркеменин кийинки версияларында даяр болот.", Toast.LENGTH_LONG).show()
////        }
//
//        btn_alty_sypat.setOnClickListener{
//            val intent = Intent(this, AltySypat::class.java)
//            startActivity(intent)
//        }
//        btnIslam_test.setOnClickListener{
////            val intent = Intent(this, IslamTest::class.java)
////            startActivity(intent)
//            Toast.makeText(this, "Азыркы учурда бул баракча даяр эмес!", Toast.LENGTH_LONG).show()
//        }
//        btnMasnunDubalar.setOnClickListener{
////            val intent = Intent(this, MasnunDubalar::class.java)
////            startActivity(intent)
//            Toast.makeText(this, "Азыркы учурда бул баракча даяр эмес!", Toast.LENGTH_LONG).show()
//        }
//        btnTasbih.setOnClickListener{
//            val intent = Intent(this, Tasbih::class.java)
//            startActivity(intent)
//        }
    }

}