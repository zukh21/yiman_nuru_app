package com.zim.yiman_nuru

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class ZikirTypesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zikir_types, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val my_telegram_btn: LinearLayout = view.findViewById(R.id.my_telegram_btn)
        my_telegram_btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/YimanNuruSupport"))
            startActivity(intent)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ZikirTypesFragment()
    }
}