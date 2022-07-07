package com.zim.yiman_nuru

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class MasnunDubalar : AppCompatActivity() {
    lateinit var changed_text: TextView
    val arrayForTextView = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masnun_dubalar)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE) // Запреть скриншот
        changed_text = findViewById(R.id.changed_text)
        arrayForTextView.add("muza")
        arrayForTextView.add("zukh")
        arrayForTextView.add("ilyo")
        arrayForTextView.add("zim")
        var count = 0
        val handler = Handler(Looper.getMainLooper())

        val hadisChange: Thread = object : Thread() {
            override fun run() {
                while (true) {
                    if (count > arrayForTextView.size-1){
                        count=0
                    }
                        sleep(1000) //1000ms = 1 sec
                        changed_text.text = arrayForTextView[count]
                    count++

                }
            }
        }

        hadisChange.start()







    }
}