package edu.uw.ischool.ctu4.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val math = findViewById<Button>(R.id.math)
        val physics = findViewById<Button>(R.id.physics)
        val marvel = findViewById<Button>(R.id.marvel)

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