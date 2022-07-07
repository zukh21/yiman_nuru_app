package com.zim.yiman_nuru

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.zim.yiman_nuru.databinding.ActivityTasbihBinding

class TasbihActivity : AppCompatActivity() {
    lateinit var binding: ActivityTasbihBinding
    lateinit var handler: Handler
    var TOTAL_TASBIH = 0
    val MAX_TASBIH = 33
    var round = 0
    private var backPressedTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasbihBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackNavTop.setOnClickListener {
            if (backPressedTime + 2000 > System.currentTimeMillis()){
                super.onBackPressed()
            }else Toast.makeText(this, "Чыгуу үчүн, дагы бир жолу басыңыз", Toast.LENGTH_SHORT).show()
            backPressedTime = System.currentTimeMillis()
        }

        var i: Int = 0
        var count = 0
        binding.talkingTasbihChange.setOnClickListener{
            i++
            handler = Handler(Looper.getMainLooper())
            val runn = Runnable { i = 0}
            if (i == 1){
                Toast.makeText(this, "Өчүрүү үчүн эки жолу басыңыз!", Toast.LENGTH_SHORT).show()
                handler.postDelayed(runn, 2000)
            }else if (i == 2){
                count = 0
                TOTAL_TASBIH = 0
                round = 0
                binding.countTasbihView.text = "${count}"
                binding.roundView.text = "круг: ${round}"
                binding.totalAmountView.text = "Тартылган тасбих: $TOTAL_TASBIH"
            } else{
                i = 0
            }

        }

        binding.talkingTasbihCount.setOnClickListener {
            count++
            TOTAL_TASBIH++
            binding.countTasbihView.text = "${count}"
            if (count == MAX_TASBIH){
                round += 1
                binding.roundView.text = "круг: ${round}"
                count = 0
            }
            binding.totalAmountView.text = "Тартылган тасбих: $TOTAL_TASBIH"
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }else Toast.makeText(this, "Чыгуу үчүн, дагы бир жолу басыңыз", Toast.LENGTH_SHORT).show()
        backPressedTime = System.currentTimeMillis()
    }
}