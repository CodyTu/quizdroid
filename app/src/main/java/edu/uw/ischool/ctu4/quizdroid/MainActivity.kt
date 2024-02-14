package edu.uw.ischool.ctu4.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val math = findViewById<Button>(R.id.math)
        val physics = findViewById<Button>(R.id.physics)
        val marvel = findViewById<Button>(R.id.marvel)

        val mathDes = findViewById<TextView>(R.id.mathDes)
        val physDes = findViewById<TextView>(R.id.physDes)
        val marvelDes = findViewById<TextView>(R.id.marvelDes)

        val quiz = QuizApp()
        math.text = quiz.getTopics()[0].title
        physics.text = quiz.getTopics()[1].title
        marvel.text = quiz.getTopics()[2].title

        mathDes.text = quiz.getTopics()[0].short_description
        physDes.text = quiz.getTopics()[1].short_description
        marvelDes.text = quiz.getTopics()[2].short_description


        math.setOnClickListener({
            val intent = Intent(this, TopicOverview::class.java)
            intent.putExtra("key", "math")
            startActivity(intent)
        })

        physics.setOnClickListener({
            val intent = Intent(this, TopicOverview::class.java)
            intent.putExtra("key", "physics")
            startActivity(intent)
        })

        marvel.setOnClickListener({
            val intent = Intent(this, TopicOverview::class.java)
            intent.putExtra("key", "marvel")
            startActivity(intent)
        })
    }
}