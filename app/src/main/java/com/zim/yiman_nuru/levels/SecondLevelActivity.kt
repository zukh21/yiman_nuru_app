package com.zim.yiman_nuru.levels

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.zim.yiman_nuru.IslamTest
import com.zim.yiman_nuru.QA
import com.zim.yiman_nuru.R
import com.zim.yiman_nuru.TestDBManager
import com.zim.yiman_nuru.databinding.ActivityFirstLevelBinding

class SecondLevelActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstLevelBinding
    var listQA = mutableListOf<QA>()
    var index = 0
    lateinit var handler: Handler
    lateinit var qa: QA
    var correctAnswerCount = 0
    var wrongAnswerCount = 0
    private var backPressedTime = 0L
    var countQuestions = 1
    val testDBManager = TestDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        testDBManager.openDB()
        handler = Handler(Looper.getMainLooper())

        showDialog()
        addQAs(QA(question = "Пайгамбарыбыз Мухаммад (САВ) хижрый жыл саноосу боюнча кайсы айда жарык дүйнөгө келген?", option1 = "Мухаррам", option2 = "Рабиъул-Аввал", option3 = "Рамадан", correct_answer = "Рабиъул-Аввал"))
        addQAs(QA(question = "Ваддаан казаты хижрый жыл саноосу боюнча кайсы жылда жана айда болгон?", option1 = "Экинчи хижра жылы Сафар айында", option2 = "Бешинчи хижра жылы Мухаррам айында", option3 = "Үчүнчү хижра жылы Рабиъул-Аввал айында", correct_answer = "Экинчи хижра жылы Сафар айында"))
        addQAs(QA(question = "Буваат казаты хижрый жыл саноосу боюнча кайсы жылда жана айда болгон?", option1 = "Экинчи хижра жылы Рабиъул-Аахир айында", option2 = "Бешинчи хижра жылы Мухаррам айында", option3 = "Экинчи хижра жылы Сафар айында", correct_answer = "Экинчи хижра жылы Рабиъул-Аахир айында"))
        addQAs(QA(question = "Пайгамбарыбыз Мухаммад (САВ) Григориан жыл саноосу боюнча кайсы айда жана канчасында жарык дүйнөгө келген?", option1 = "31-декабрь", option2 = "13-сентябрь", option3 = "20-август", correct_answer = "20-август"))
        addQAs(QA(question = "Сүйүктүү Пайгамбарыбыз Мухаммад (САВ) Григориан жыл саноосу боюнча кайсы жылы жарык дүйнөгө келген?", option1 = "570-571-жылдары", option2 = "305-жылы", option3 = "650-823-жылдары", correct_answer = "570-571-жылдары"))
        addQAs(QA(question = "Пайгамбарыбыз (саллаллааху алайхи ва саллам)дын атасы ким?", option1 = "Абдулла", option2 = "Умар", option3 = "Абдул Мутталиб", correct_answer = "Абдулла"))
        addQAs(QA(question = "Пайгамбарыбыз (саллаллааху алайхи ва саллам)дын апасы ким?", option1 = "Амина", option2 = "Айша", option3 = "Фатима", correct_answer = "Амина"))
        addQAs(QA(question = "Абдул Мутталиб пайгамбарыбыздын кандай жакыны?", option1 = "Чоӊ-атасы", option2 = "Сахабасы", option3 = "Таякеси", correct_answer = "Чоӊ-атасы"))
        addQAs(QA(question = "Абу Талиб пайгамбарыбыздын кандай жакыны?", option1 = "Чоӊ-атасы", option2 = "Сахабасы", option3 = "Агасы (атасынын иниси)", correct_answer = "Агасы (атасынын иниси)"))
        addQAs(QA(question = "Алланын элчиси (саллаллааху алайхи ва саллам) канча жашында биринчи жолу үйлөнгөн?", option1 = "40 жашында", option2 = "25 жашында", option3 = "23 жашында", correct_answer = "25 жашында"))
        addQAs(QA(question = "Алланын элчиси (саллаллааху алайхи ва саллам) дын биринчи аялы ким?", option1 = "Хадийжа (разияллоху анха)", option2 = "Фатима (разияллоху анха)", option3 = "Амина (разияллоху анха)", correct_answer = "Хадийжа (разияллоху анха)"))
        addQAs(QA(question = "Кааба  кайра курулганда Мухаммад (саллаллааху алайхи ва саллам) канча жашта болгон?", option1 = "33 жашта", option2 = "40 жашта", option3 = "35 жашта", correct_answer = "35 жашта"))
        addQAs(QA(question = "Мухаммад (САВ) канча жашында пайгамбар экенин жарыялаган?", option1 = "62 жашта", option2 = "25 жашта", option3 = "40 жашта", correct_answer = "40 жашта"))
        addQAs(QA(question = "Сүйүктүү Пайгамбарыбыз Мухаммад (саллаллааху алайхи ва саллам)дын балдары?", option1 = "7, 3 уул - 4 кыз", option2 = "6, 2 уул - 4 кыз", option3 = "5, 2 уул - 3 кыз", correct_answer = "7, 3 уул - 4 кыз"))
        addQAs(QA(question = "Биринчи даават борбору _____.", option1 = "Дар-э-Аркам", option2 = "Мадина ш", option3 = "Ухуд", correct_answer = "Дар-э-Аркам"))
        listQA.shuffle()
        qa = listQA[index]
        setAllQA()
        optionOneClicked(binding.option1)
        optionTwoClicked(binding.option2)
        optionThreeClicked(binding.option3)



        AdsYandex().ads(binding.adView) //        Рекламный блок
    }


    fun addQAs(qa: QA){
        listQA.add(qa)
    }

    fun setAllQA(){
        binding.questionLine.text = qa.question
        binding.option1.text = qa.option1
        binding.option2.text = qa.option2
        binding.option3.text = qa.option3
    }

    fun enableButton(){
        binding.option1.isClickable=true
        binding.option2.isClickable=true
        binding.option3.isClickable=true
    }
    fun disableButton(){
        binding.option1.isClickable=false
        binding.option2.isClickable=false
        binding.option3.isClickable=false
    }

    fun resetBackground(){
        binding.option1.setBackgroundResource(R.drawable.answers_bg_stroke)
        binding.option2.setBackgroundResource(R.drawable.answers_bg_stroke)
        binding.option3.setBackgroundResource(R.drawable.answers_bg_stroke)
    }

    fun onFinish(){
        index++


        if (index < listQA.size){
            qa = listQA[index]
            handler.postDelayed({
                setAllQA()
                resetBackground()
                enableButton()
                countQuestions++
                binding.countQuestionsView.text = "Суроо: $countQuestions"

            }, 500)
        }else{
            handler.postDelayed({
                gameResult()
            }, 500)
            if (correctAnswerCount == listQA.size){
                testDBManager.insertToDB(3)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        testDBManager.closeDB()
    }

    private fun currectAnswer(option: TextView){
        option.setBackgroundResource(R.drawable.answers_bg_change_color_green)
        correctAnswerCount++
    }

    private fun wrongAnswer(option: TextView){
        option.setBackgroundResource(R.drawable.answers_bg_change_color_red)
        wrongAnswerCount++
    }
    private fun changeBgCorrect(option: TextView){
        option.setBackgroundResource(R.drawable.answers_bg_change_color_green)
    }

    fun optionOneClicked(view: View){
        view.setOnClickListener {
            if (qa.option1 == qa.correct_answer){
//            binding.option1.setTextColor(Color.GREEN)
                currectAnswer(binding.option1)
            }else{
                wrongAnswer(binding.option1)
                if (qa.option2 == qa.correct_answer){
                    changeBgCorrect(binding.option2)
                }else changeBgCorrect(binding.option3)

            }

            disableButton()
            onFinish()
        }

    }
    fun optionTwoClicked(view: View){

        view.setOnClickListener {
            if (qa.option2 == qa.correct_answer){
//            binding.option1.setTextColor(Color.GREEN)

                currectAnswer(binding.option2)
            }else{
                wrongAnswer(binding.option2)
                if (qa.option1 == qa.correct_answer){
                    changeBgCorrect(binding.option1)
                }else changeBgCorrect(binding.option3)
            }

            disableButton()
            onFinish()
        }
    }
    fun optionThreeClicked(view: View){

        view.setOnClickListener {
            if (qa.option3 == qa.correct_answer){
//            binding.option1.setTextColor(Color.GREEN)

                currectAnswer(binding.option3)
            }else{
                wrongAnswer(binding.option3)
                if (qa.option2 == qa.correct_answer){
                    changeBgCorrect(binding.option2)
                }else changeBgCorrect(binding.option1)
            }

            disableButton()
            onFinish()
        }
    }

    fun gameResult(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.test_result)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val currentAnswerLine = dialog.findViewById<TextView>(R.id.currentAnswer)
        val wrongAnswerLine = dialog.findViewById<TextView>(R.id.wrongAnswer)
        val motiv = dialog.findViewById<TextView>(R.id.motivText)
        dialog.setCancelable(false)
        currentAnswerLine.text = "Туура жооптор: $correctAnswerCount"
        wrongAnswerLine.text = "Ката жооптор: $wrongAnswerCount"
        val btnNext = dialog.findViewById(R.id.btnNext) as Button
        val closeDialog = dialog.findViewById(R.id.close_dialog) as ImageView

        if (correctAnswerCount > listQA.size / 2 && correctAnswerCount < listQA.size) {
            motiv.text = getString(R.string.norm_and_recreate_this_level)
            btnNext.text = getString(R.string.restart_level)
            btnNext.setOnClickListener {
                recreate()
                dialog.dismiss()
            }
            closeDialog.setOnClickListener {
                startActivity(Intent(this, IslamTest::class.java))
                finish()
                dialog.dismiss()
            }
        } else if (correctAnswerCount == listQA.size) {
            motiv.text = getString(R.string.best_and_to_next_level)
            btnNext.setOnClickListener {
                dialog.dismiss()
                startActivity(Intent(this, IslamTest::class.java))
                finish()

            }
            closeDialog.setOnClickListener {
                startActivity(Intent(this, IslamTest::class.java))
                finish()
                dialog.dismiss()
            }
        } else {
            motiv.text = getString(R.string.bad_and_recreate_this_level)
            btnNext.text = getString(R.string.restart_level)
            btnNext.setOnClickListener {
                recreate()
                dialog.dismiss()
            }
            closeDialog.setOnClickListener {
                startActivity(Intent(this, IslamTest::class.java))
                finish()
                dialog.dismiss()
            }
        }

        dialog.show()
    }





    fun showDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_for_game_test)
        val btnContinue = dialog.findViewById(R.id.btnContinue) as Button
        val closeDialog = dialog.findViewById(R.id.close_dialog) as ImageView
        val textAlertDialog = dialog.findViewById<TextView>(R.id.text_alert_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        textAlertDialog.text = getText(R.string.text_for_level_second)
        btnContinue.setOnClickListener {
            dialog.dismiss()
        }
        closeDialog.setOnClickListener {
            startActivity(Intent(this, IslamTest::class.java))
            finish()
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onBackPressed() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_for_exit_game)
        val btnStay = dialog.findViewById(R.id.btnStay) as Button
        val btnExit = dialog.findViewById(R.id.btnExit) as Button
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btnStay.setOnClickListener {
            dialog.dismiss()
        }
        btnExit.setOnClickListener {
            super.onBackPressed()
            startActivity(Intent(this, IslamTest::class.java))
        }
        dialog.show()
    }
}