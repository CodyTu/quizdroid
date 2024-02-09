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
        val descriptionTxt = findViewById<TextView>(R.id.description)
        when(topic) {
            "math" -> descriptionTxt.setText(getString(R.string.math))
            "physics" -> descriptionTxt.setText(getString(R.string.physics))
            "marvel" -> descriptionTxt.setText(getString(R.string.marvel))
        }
        val totalQuestions = findViewById<TextView>(R.id.totalQuestions)
        var currentTopic: List<String> = resources.getStringArray(R.array.mathQ).asList()
        when(topic) {
            "physics" -> currentTopic = resources.getStringArray(R.array.physicsQ).asList()
            "marvel" -> currentTopic = resources.getStringArray(R.array.marvelQ).asList()
        }

        totalQuestions.setText("Total Number of Questions: ${currentTopic.size}")

        val begin = findViewById<Button>(R.id.begin)
        begin.setOnClickListener({
            val intent = Intent(this, Questions::class.java)
            intent.putExtra("currentTopic", currentTopic.toTypedArray())
            intent.putExtra("currentQuestion", 0)
            intent.putExtra("score", 0)
            intent.putExtra("topic", topic)
            startActivity(intent)
        })

    }
}