package com.zim.yiman_nuru

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Runnable

class TasbihFragment : Fragment() {
    private lateinit var handler: Handler
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_tasbih, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var i: Int = 0
        var count = 0
        val btn_talking_tasbih_count: ImageView = view.findViewById(R.id.talking_tasbih_count)
        val count_tasbih_view: TextView = view.findViewById(R.id.count_tasbih_view)

        val talking_tasbih_change: ImageView = view.findViewById(R.id.talking_tasbih_change)
        val mediaSubhaanallah: MediaPlayer = MediaPlayer.create(activity, R.raw.subhaanallah)
        val mediaAlhamdulillah: MediaPlayer = MediaPlayer.create(activity, R.raw.alhamdulillah)
        val mediaAllohuakbar: MediaPlayer = MediaPlayer.create(activity, R.raw.allohuakbar)
        val talking_tasbih_volume_up: ImageView = view.findViewById(R.id.talking_tasbih_volume_up)
        val talking_tasbih_volume_off: ImageView = view.findViewById(R.id.talking_tasbih_volume_off)
        talking_tasbih_volume_up.setOnClickListener {
            mediaSubhaanallah.setVolume(0f, 0f)
            mediaAlhamdulillah.setVolume(0f, 0f)
            mediaAllohuakbar.setVolume(0f, 0f)
            talking_tasbih_volume_up.setVisibility(View.INVISIBLE)
            talking_tasbih_volume_off.setVisibility(View.VISIBLE)
        }
        talking_tasbih_volume_off.setOnClickListener {
            mediaSubhaanallah.setVolume(0.7f, 0.7f)
            mediaAlhamdulillah.setVolume(0.7f, 0.7f)
            mediaAllohuakbar.setVolume(0.7f, 0.7f)
            talking_tasbih_volume_up.setVisibility(View.VISIBLE)
            talking_tasbih_volume_off.setVisibility(View.INVISIBLE)
        }
        talking_tasbih_change.setOnClickListener{
                i++
                handler = Handler(Looper.getMainLooper())
                val runn = Runnable { i = 0}
                if (i == 1){
                    Toast.makeText(context, "Өчүрүү үчүн эки жолу басыңыз!", Toast.LENGTH_SHORT).show()
                    handler.postDelayed(runn, 500)
                }else if (i == 2){
                    count = 0
                    count_tasbih_view.text = "${count}"
                } else{
                    i = 0
                }

        }
        btn_talking_tasbih_count.setOnClickListener {
            count++
            count_tasbih_view.text = "${count}"
            if (count in 0 .. 33){
                if (count == 1){
                    mediaSubhaanallah.start()
                }else if (count == 33){
                    mediaAlhamdulillah.start()
                }
            }else if (count in 34 .. 66) {
                if (count == 66){
                    mediaAllohuakbar.start()
                }
            }else if (count in 67 .. 99){
            }

        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = TasbihFragment()
    }
}

