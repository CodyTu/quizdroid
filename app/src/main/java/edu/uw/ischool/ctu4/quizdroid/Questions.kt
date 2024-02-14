package edu.uw.ischool.ctu4.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class Questions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        val quiz = QuizApp()
        val currentQ = intent.getIntExtra("currentQuestion", 0)
        val score = intent.getIntExtra("score", 0)
        val topic = intent.getStringExtra("topic")
//        val myChoicesM: List<List<String>> = listOf(
//            listOf("20", "12", "14", "21"),
//            listOf("0", "2", "4", "6"),
//            listOf("17", "15", "5", "10"),
//            listOf("1", "2", "3", "4"),
//            listOf("960 ft", "96 ft", "0.96 ft", "0.096 ft")
//            )
//        val myChoicesP: List<List<String>> = listOf(
//            listOf("Zero", "Negative", "Positive", "Infinity"),
//            listOf("Kilometer", "Centimeter", "Meter", "Millimetre")
//        )
//        val myChoicesMv: List<List<String>> = listOf(
//            listOf("Hulk", "Iron Man", "Thor", "Captain America"),
//            listOf("Bruce Banner", "Steve Rogers", "Tony Stark", "Peter Parker"),
//            listOf("Mjolnir", "Stormbreaker", "Gungnir", "Iron Hammer")
//        )

        val question = findViewById<TextView>(R.id.question)
        val radioGroup = findViewById<RadioGroup>(R.id.choices)

        val choice1 = findViewById<RadioButton>(R.id.choice1)
        val choice2 = findViewById<RadioButton>(R.id.choice2)
        val choice3 = findViewById<RadioButton>(R.id.choice3)
        val choice4 = findViewById<RadioButton>(R.id.choice4)

        var current = quiz.getTopics()[0].questions[currentQ]
        when (topic) {
            "physics" -> current = quiz.getTopics()[1].questions[currentQ]
            "marvel" -> current = quiz.getTopics()[2].questions[currentQ]
        }

        question.text = current.question

        choice1.setText(current.answers[0])
        choice2.setText(current.answers[1])
        choice3.setText(current.answers[2])
        choice4.setText(current.answers[3])

        val submit = findViewById<Button>(R.id.submit)
        submit.setOnClickListener({
            if (radioGroup.checkedRadioButtonId != -1) {
                val selectedId = radioGroup.checkedRadioButtonId
                val selectedChoice = findViewById<RadioButton>(selectedId)
                val intent = Intent(this, Answers::class.java)
                intent.putExtra("currentQuestion", currentQ)
                intent.putExtra("score", score)
                intent.putExtra("answer", selectedChoice.text)
                intent.putExtra("topic", topic)
                startActivity(intent)
            }
        })
    }
}