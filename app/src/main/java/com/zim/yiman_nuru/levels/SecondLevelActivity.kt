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
        addQAs(QA("SАкыркы пайгамбар ким?","Муса", "Ибрахим", "Мухаммад (САВ)", "Мухаммад (САВ)"))
        addQAs(QA("SЫйык диндердин эң акыркысы?", "Ислам","Христин", "Буддизм",  "Ислам"))
        addQAs(QA("SИслам аалымдарынын көз караштары боюнча кыз балдардын балагат жашы канча жаш болуп эсептелет", "9-18 жаш","7-9 жаш", "9-15 жаш",  "9-15 жаш"))
        addQAs(QA("SИслам дининде жасалышы жана пайдаланышы кескин түрдө тыюу салынган нерселер.", "Мандуб","Харам", "Макрух",  "Харам"))
        addQAs(QA("SИсламга кирүү үчүн эмне кылуу керек?", "Орозо тутуу", "Шахадат келмесин айтуу","Намаз окуу", "Шахадат келмесин айтуу"))
        addQAs(QA("SИслам беш нерсенин үстүнө курулган: Келиме келтирүү, намаз окуу, орозо тутуу, ______, ажылыкка баруу", "Зекет берүү", "Илим алуу","Жакшылыкка чакыруу", "Зекет берүү"))
        addQAs(QA("SИслам аалымдарынын көз караштары боюнча эркек балдардын балагат жашы канча жаш болуп эсептелет?", "9-18 жаш", "7-9 жаш","12-15 жаш", "12-15 жаш"))
        addQAs(QA("SБелгилүү мал-мүлктүн түрлөрүн белгилүү бир бөлүгүн Аллах куранда көрсөткөн айрым мусулмандарга менчиктеп берүү. Бул ибадат парз.","Садака", "Зекет", "Салык", "Зекет"))
        addQAs(QA(question = "S'_____ - Ыймандын жарымы' (хадис)", option1 = "Тазалык", option2 = "Такыбалык", option3 = "Жылмаюу", correct_answer = "Тазалык"))
        addQAs(QA(question = "SИслам дининде рамазан айында орозо кармоо ____", option1 = "Важиб", option2 = "Парз", option3 = "Нафил", correct_answer = "Парз"))
        addQAs(QA(question = "SШариат белгилеген убакытта атайын белгиленген амалдар менен Байтул харамды зыярат кылуу ибадаты эмне деп аталат?", option1 = "Намаз", option2 = "Ажылык", option3 = "Саякат", correct_answer = "Ажылык"))
        addQAs(QA(question = "SАчык айкын, күмөнсүз далилдер менен келген Алла Тааланын, же пайгамбарынын (САВ) буйруктарына карата айтылат. М: Беш убак намаз окуу, орозо кармоо, ж.б", option1 = "Парз", option2 = "Сүннөт", option3 = "Важиб", correct_answer = "Парз"))
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
            motiv.text = "Баракелде кичине аракет кылыңыз"
            btnNext.text = "Кайра баштоо"
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
            motiv.text = "Баракелде өтө жакшы!"
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
            motiv.text = "Жамааан, кайрадан аракет кылыңыз"
            btnNext.text = "Кайра баштоо"
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