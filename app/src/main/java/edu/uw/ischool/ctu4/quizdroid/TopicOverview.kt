package edu.uw.ischool.ctu4.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class TopicOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        val topic: String = intent.getStringExtra("key").toString()

        val quiz = QuizApp()
        val descriptionTxt = findViewById<TextView>(R.id.description)
        val overviewTitle = findViewById<TextView>(R.id.overviewTitle)
        when(topic) {
            "math" -> {
                overviewTitle.text = quiz.getTopics()[0].title
                descriptionTxt.setText(quiz.getTopics()[0].long_description)
            }
            "physics" -> {
                overviewTitle.text = quiz.getTopics()[1].title
                descriptionTxt.setText(quiz.getTopics()[1].long_description)
            }
            "marvel" -> {
                overviewTitle.text = quiz.getTopics()[2].title
                descriptionTxt.setText(quiz.getTopics()[2].long_description)
            }
        }

        val totalQuestions = findViewById<TextView>(R.id.totalQuestions)
        var currentTopic: List<TopicRepository.Question> = quiz.getTopics()[0].questions
        when(topic) {
            "physics" -> currentTopic = quiz.getTopics()[1].questions
            "marvel" -> currentTopic = quiz.getTopics()[2].questions
        }

        totalQuestions.setText("Total Number of Questions: ${currentTopic.size}")

        val begin = findViewById<Button>(R.id.begin)
        begin.setOnClickListener({
            val intent = Intent(this, Questions::class.java)
            intent.putExtra("currentQuestion", 0)
            intent.putExtra("score", 0)
            intent.putExtra("topic", topic)
            startActivity(intent)
        })

    }
}