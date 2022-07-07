package com.zim.yiman_nuru

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.zim.yiman_nuru.databinding.FragmentMainBinding
import kotlin.random.Random


class MainFragment : Fragment() {
    val hadisesArray = mutableListOf<Hadises>()
    var hadisId = 0
    lateinit var binding: FragmentMainBinding

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
        val btnAllahtynYsymdary = view.findViewById<CardView>(R.id.btn_allahtyn_ysymdary)
        val btnAltySypat = view.findViewById<CardView>(R.id.btn_alty_sypat)
        val btnMasnunDuba = view.findViewById<CardView>(R.id.btn_masnun_duba)
        val btnZikir = view.findViewById<CardView>(R.id.btn_zikir)
        btnAllahtynYsymdary.setOnClickListener {
//            val intent = Intent(context, AllahtynYsymdary::class.java)
//            startActivity(intent)
            Toast.makeText(context, "Азырынча жасалууда", Toast.LENGTH_SHORT).show()
        }

        btnAltySypat.setOnClickListener {
            val intent = Intent(context, AltySypat::class.java)
            startActivity(intent)
        }

        btnMasnunDuba.setOnClickListener(View.OnClickListener {
//            val intent = Intent(context, MasnunDubalar::class.java)
//            startActivity(intent)
            Toast.makeText(context, "Азырынча жасалууда", Toast.LENGTH_SHORT).show()
        })

        btnZikir.setOnClickListener{
            val intent = Intent(context, Zikir::class.java)
            startActivity(intent)
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}