package com.zim.yiman_nuru

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.widget.Button
import android.widget.TextView
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
            val dialog = Dialog(this)
            dialog.setCancelable(false)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_for_exit_game)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val btnStay = dialog.findViewById<Button>(R.id.btnStay)
            val text_alert_dialog = dialog.findViewById<TextView>(R.id.text_alert_dialog)
            val btnExit = dialog.findViewById<Button>(R.id.btnExit)
            text_alert_dialog.text = "Азыр чыксаңыз тарткан бардык зикириңиз нөлгө барабарланат!"
            btnStay.setOnClickListener {
                dialog.dismiss()
            }
            btnExit.setOnClickListener {
                finish()
                dialog.dismiss()
            }
            dialog.show()
        }

        var count = 0
        binding.talkingTasbihChange.setOnClickListener{
            val dialog = Dialog(this)
            dialog.setCancelable(false)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_for_exit_game)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val btnStay = dialog.findViewById<Button>(R.id.btnStay)
            val text_alert_dialog = dialog.findViewById<TextView>(R.id.text_alert_dialog)
            val btnExit = dialog.findViewById<Button>(R.id.btnExit)
            text_alert_dialog.text = "Тарткан зикирлериңизди өчүрүүнү каалайсызбы?"
            btnStay.text = "Ооба"
            btnExit.text = "Жок"
            btnExit.setOnClickListener {
                dialog.dismiss()
            }
            btnStay.setOnClickListener {
                dialog.dismiss()
                count = 0
                TOTAL_TASBIH = 0
                round = 0
                binding.countTasbihView.text = "${count}"
                binding.roundView.text = "круг: ${round}"
                binding.totalAmountView.text = "Тартылган тасбих: $TOTAL_TASBIH"
            }
            dialog.show()


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
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_for_exit_game)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnStay = dialog.findViewById<Button>(R.id.btnStay)
        val text_alert_dialog = dialog.findViewById<TextView>(R.id.text_alert_dialog)
        val btnExit = dialog.findViewById<Button>(R.id.btnExit)
        text_alert_dialog.text = "Чыксаңыз тарткан бардык зикириңиз өчөт!"
        btnStay.setOnClickListener {
            dialog.dismiss()
        }
        btnExit.setOnClickListener {
            finish()
            dialog.dismiss()
        }
        dialog.show()
    }
}