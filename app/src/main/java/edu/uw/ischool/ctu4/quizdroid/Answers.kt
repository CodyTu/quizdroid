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
//        val currentTopic = intent.getStringArrayExtra("currentTopic")?.asList()
        var score = intent.getIntExtra("score", 0)
        val topic = intent.getStringExtra("topic")
        val quiz = QuizApp()
//        val correctAnswersM: List<String> = listOf("21", "2", "17", "1", "96 ft")
//        val correctAnswersP: List<String> = listOf("Negative", "Meter")
//        val correctAnswersMv: List<String> = listOf("Thor", "Tony Stark", "Mjolnir")

        val correct = findViewById<TextView>(R.id.correct)
        val outOf = findViewById<TextView>(R.id.outOf)
        val next = findViewById<Button>(R.id.next)

        val url = "http://tednewardsandbox.site44.com/questions.json"

        var current = quiz.getTopics()[0]
        when(topic) {
            "physics" -> current = quiz.getTopics()[1]
            "marvel" -> current = quiz.getTopics()[2]
        }
        val correctAnswer = current.questions[currentQ].answers[(current.questions[currentQ].correctAnswer) - 1]

        correct.setText("Your Answer: ${answer}; Correct Answer: ${correctAnswer}")

        if (answer == correctAnswer) {
            score += 1
        }
        outOf.setText("You have ${score} out of ${current.questions.size}")

        if(currentQ == current.questions.size - 1) {
            next.setText("Finish")
        } else {
            next.setText("Next")
        }

            next.setOnClickListener({
                if(currentQ < current.questions.size - 1) {
                    val intent = Intent(this, Questions::class.java)
                    intent.putExtra("currentQuestion", currentQ + 1)
                    intent.putExtra("score", score)
                    intent.putExtra("topic", topic)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            })

    }
}