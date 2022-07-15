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

class SeventhLevelActivity : AppCompatActivity() {
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
        addQAs(QA(question = "Ал, “Мен кудаймын” деп чектен чыккан Фираунду динге чакырган, бул пайгамбар ким?", option1 = "Солих (АС)", option2 = "Муса (АС)", option3 = "Юнус (АС)", correct_answer = "Муса (АС)"))
        addQAs(QA(question = "Куранда: “Биз ____ Өз аят-белгилерибиз жана анык далил менен жибердик” – деп айтылган пайгамбар ____", option1 = "Муса (АС)", option2 = "Дауд (АС)", option3 = "Иса (АС)", correct_answer = "Муса (АС)"))
        addQAs(QA(question = "Ал “Зуннун” деп да аталат, бул пайгамбар ____", option1 = "Юнус (АС)", option2 = "Муса (АС)", option3 = "Солих (АС)", correct_answer = "Юнус (АС)"))
        addQAs(QA(question = "Алла Таала аны сыноо үчүн китке жуттуруп жиберет. Ал киттин ичинде жаткан мезгилде да Аллага мактоо айтып, Ага дуба кылып, Аны эсинен чыгарбай сыноодон өткөн, бул пайгамбар ____", option1 = "Адам (АС)", option2 = "Юнус (АС)", option3 = "Мухаммад (САВ)", correct_answer = "Юнус (АС)"))
        addQAs(QA(question = "Иляс пайгамбардын окуучусу бул кайсы пайгамбар?", option1 = "Аляса (АС)", option2 = "Худ (АС)", option3 = "Муса (АС)", correct_answer = "Аляса (АС)"))
        addQAs(QA(question = "Ал Димашко тарапка пайгамбар болуп жиберилген, бул пайгамбар ____", option1 = "Закария (АС)", option2 = "Нух (АС)", option3 = "Иляс (АС)", correct_answer = "Иляс (АС)"))
        addQAs(QA(question = "Куранда: “Исмайилди, Алясаьны, ____ эстегин. Баардыгы жакшылардан болчу” – деп айтылган жана ал дагы “Бану Исраил” пайгамбарларынын бири, кайсы пайгамбар тууралуу сөз болуп жатат?", option1 = "Муса (АС)", option2 = "Зул-Кифл (АС)", option3 = "Дауд (АС)", correct_answer = "Зул-Кифл (АС)"))
        addQAs(QA(question = "Ал бир канча жыл катуу оору менен ооруган. Бирок, ооруга катуу сабыр кылган, кайсы пайгамбар тууралуу сөз кылынууда?", option1 = "Аюб (АС)", option2 = "Нух (АС)", option3 = "Адам (АС)", correct_answer = "Аюб (АС)"))
        addQAs(QA(question = "Ал Мадян элине пайгамбар болуп жиберилген, ____ пайгамбар аларды таразадан алдоону таштоого чакырды. Бирок, алар ага көнүшкөн жок, кайсы пайгамбар тууралуу сөз болуп жатат?", option1 = "Аюб (АС)", option2 = "Иса (АС)", option3 = "Шуайб (АС)", correct_answer = "Шуайб (АС)"))
        addQAs(QA(question = "Анын коому бачабаздык менен алек болуп бузулуп кеткен болчу. Ал коомун туура жолго кайтаруу үчүн даават кылат. Бирок, эл аны укпайт. Акырында Алла Таала аларды жер алдына жуткуруп жок кылат, кайсы пайгамбардын коому?", option1 = "Лут (АС)", option2 = "Шуайб (АС)", option3 = "Зул-Кифл (АС)", correct_answer = "Лут (АС)"))
        addQAs(QA(question = "Ал Якуб пайгамбардын баласы болгон. Ал бала кезинде бир тууган агалары тарабынан көптөгөн кордуктарды көрөт. Кийин Алланын каалоосу менен Египеттин падышасына кеңешчи жана казына башчысы болот. Ал өтө сулуу болгон. Алла Таала ага түш жоруу илимин берген, бул кайсы пайгамбар?", option1 = "Зул-Кифл (АС)", option2 = "Юсуф (АС)", option3 = "Мухаммад (САВ)", correct_answer = "Юсуф (АС)"))
        addQAs(QA(question = "Ал Исхак пайгамбардын баласы. Анын бир аты Исраил болгон. Жөөттөрдүн “Бану Исраил” коому деп аталып калуусу ошондон улам болгон, сөз кайсы пайгамбар жөнүндө болууда?", option1 = "Шуайб (АС)", option2 = "Якуб (АС)", option3 = "Лут (АС)", correct_answer = "Якуб (АС)"))
        addQAs(QA(question = "Жөөттөр анын урпактары болуп эсептелет, бул пайгамбар ____", option1 = "Исхак (АС)", option2 = "Солих (АС)", option3 = "Муса (АС)", correct_answer = "Исхак (АС)"))
        addQAs(QA(question = "Пайгамбарыбыз Мухаммаддын (САВ) санжыра башындагы чоң атасы ____", option1 = "Исмайил (АС)", option2 = "Исхак (АС)", option3 = "Муса (АС)", correct_answer = "Исмайил (АС)"))
        addQAs(QA(question = "____ пайгамбар Алла Тааланын досу (Халии луллоохи) жана пайгамбарлардын атасы деп аталат. Анткени кийинки пайгамбарлардын баары анын урпактарынан болгон. Ал Ирак жергесине пайгамбар болуп жиберилген, бул пайгамбар ____", option1 = "Ибрахим (АС)", option2 = "Дауд (АС)", option3 = "Якуб (АС)", correct_answer = "Ибрахим (АС)"))
        addQAs(QA(question = "Ал Хижаз жана Табук аймагында жашаган Самуд уруусуна пайгамбар болгон, кайсы пайгамбар?", option1 = "Солих (АС)", option2 = "Муса (АС)", option3 = "Исхак (АС)", correct_answer = "Солих (АС)"))

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
                binding.countQuestionsView.text = "${getString(R.string.question_at_time_text)} ${countQuestions}"
                binding.allQuestionsView.text = "${getString(R.string.all_questions_text)} ${listQA.size}"

            }, 500)
        }else{
            handler.postDelayed({
                gameResult()
            }, 500)
            if (correctAnswerCount == listQA.size){
                testDBManager.insertToDB(8)
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
        textAlertDialog.text = getText(R.string.text_for_level_seventh)
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