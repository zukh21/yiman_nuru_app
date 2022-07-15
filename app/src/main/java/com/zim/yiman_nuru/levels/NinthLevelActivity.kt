package com.zim.yiman_nuru.levels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zim.yiman_nuru.R
import com.zim.yiman_nuru.databinding.SampleQaScreenBinding

class NinthLevelActivity : AppCompatActivity() {
    lateinit var binding: SampleQaScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SampleQaScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}