package com.zim.yiman_nuru.levels

import android.app.Dialog
import android.content.Intent
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
import com.zim.yiman_nuru.IslamTest
import com.zim.yiman_nuru.QA
import com.zim.yiman_nuru.R
import com.zim.yiman_nuru.TestDBManager
import com.zim.yiman_nuru.databinding.SampleQaScreenBinding

class SixthLevelActivity : AppCompatActivity() {
    lateinit var binding: SampleQaScreenBinding
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
        binding = SampleQaScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        testDBManager.openDB()
        handler = Handler(Looper.getMainLooper())

        showDialog()
        addQAs(QA(question = "Жөөттөр менен христиандар аны айкашкан жыгачка асып өлтүрүлгөн деген жалган жана шектүү ишеним менен калышкан, бул пайгамбар ____", option1 = "Ибрахим (АС)", option2 = "Иса (АС)", option3 = "Худ (АС)", correct_answer = "Иса (АС)"))
        addQAs(QA(question = "Алла Таала аны тирүү бойдон асманга чыгарып кеткен, бул пайгамбар ____", option1 = "Адам (АС)", option2 = "Идрис (АС)", option3 = "Иса (АС)", correct_answer = "Иса (АС)"))
        addQAs(QA(question = "Үнү өтө кооз болгон. Алланын каалоосу менен темирди камырдай жууруп иштеткен, бул пайгамбар ____", option1 = "Дауд (АС)", option2 = "Иса (АС)", option3 = "Ибрахим (АС)", correct_answer = "Дауд (АС)"))
        addQAs(QA(question = "Ал Закария пайгамбардын картайганда көргөн баласы, кайсы пайгамбар?", option1 = "Ибрахим (АС)", option2 = "Иса (АС)", option3 = "Яхья (АС)", correct_answer = "Яхья (АС)"))
        addQAs(QA(question = "Ал жыгач уста болгон, кайсы пайгамбар?", option1 = "Закария (АС)", option2 = "Мухаммад (САВ)", option3 = "Адам (АС)", correct_answer = "Закария (АС)"))
        addQAs(QA(question = "Ал Дауд пайгамбардын баласы, бул пайгамбар ____", option1 = "Яхья (АС)", option2 = "Сулайман (АС)", option3 = "Ибрахим (АС)", correct_answer = "Сулайман (АС)"))
        addQAs(QA(question = "Алла Таала ага куштардын тилин түшүнүү, шамалды жана жиндерди башкаруу мүмкүнчүлүгүн берген, кайсы пайгамбарга?", option1 = "Сулайман (АС)", option2 = "Иса (АС)", option3 = "Мухаммад (САВ)", correct_answer = "Сулайман (АС)"))
        addQAs(QA(question = "Ал эң биринчи самынды колдонгон, бул пайгамбар ким?", option1 = "Солих (АС)", option2 = "Сулайман (АС)", option3 = "Адам (АС)", correct_answer = "Сулайман (АС)"))
        addQAs(QA(question = "Куранда: “____га жиндер, инсандар жана куштардан куралган аскерлери чогулуп, тизилип турушту” – деп эскерилген, кайсы пайгамбар?", option1 = "Сулайман (АС)", option2 = "Адам (АС)", option3 = "Иса (АС)", correct_answer = "Сулайман (АС)"))
        addQAs(QA(question = "Картайганга чейин балалуу болгон эмес, бул пайгамбар ____", option1 = "Солих (АС)", option2 = "Адам (АС)", option3 = "Закария (АС)", correct_answer = "Закария (АС)"))

        listQA.shuffle()
        qa = listQA[index]
        setAllQA()
        optionOneClicked(binding.option1)
        optionTwoClicked(binding.option2)
        optionThreeClicked(binding.option3)



        AdsYandex().ads(binding.adView) //        Рекламный блок
        binding.countQuestionsView.text = "${getString(R.string.question_at_time_text)} ${countQuestions}"
        binding.allQuestionsView.text = "${getString(R.string.all_questions_text)} ${listQA.size}"
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
                binding.countQuestionsView.text = "${getString(R.string.question_at_time_text)} ${countQuestions}"
                binding.allQuestionsView.text = "${getString(R.string.all_questions_text)} ${listQA.size}"

            }, 500)
        }else{
            handler.postDelayed({
                gameResult()
            }, 500)
            if (correctAnswerCount == listQA.size){
                testDBManager.insertToDB(7)
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
        val textAlertDialog = dialog.findViewById(R.id.text_alert_dialog) as TextView
        textAlertDialog.text = getText(R.string.text_for_level_sixth)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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