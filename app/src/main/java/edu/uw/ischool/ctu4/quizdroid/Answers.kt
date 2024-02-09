package edu.uw.ischool.ctu4.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class Answers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answers)

        val answer = intent.getStringExtra("answer")
        val currentQ = intent.getIntExtra("currentQuestion", 0)
        val currentTopic = intent.getStringArrayExtra("currentTopic")?.asList()
        var score = intent.getIntExtra("score", 0)
        val topic = intent.getStringExtra("topic")
        val correctAnswersM: List<String> = listOf("21", "2", "17", "1", "96 ft")
        val correctAnswersP: List<String> = listOf("Negative", "Meter")
        val correctAnswersMv: List<String> = listOf("Thor", "Tony Stark", "Mjolnir")

        val correct = findViewById<TextView>(R.id.correct)
        val outOf = findViewById<TextView>(R.id.outOf)
        val next = findViewById<Button>(R.id.next)

        var current = correctAnswersM
        when(topic) {
            "physics" -> current = correctAnswersP
            "marvel" -> current = correctAnswersMv
        }

        correct.setText("Your Answer: ${answer}; Correct Answer: ${current[currentQ]}")

        if (answer == current[currentQ]) {
            score += 1
        }
        outOf.setText("You have ${score} out of ${currentTopic?.size}")

        if(currentQ == currentTopic?.size?.minus(1) ?: "") {
            next.setText("Finish")
        } else {
            next.setText("Next")
        }

            next.setOnClickListener({
                if(currentQ != currentTopic?.size?.minus(1) ?: "") {
                    val intent = Intent(this, Questions::class.java)
                    intent.putExtra("currentQuestion", currentQ + 1)
                    intent.putExtra("score", score)
                    intent.putExtra("currentTopic", currentTopic?.toTypedArray())
                    intent.putExtra("topic", topic)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            })

    }
}