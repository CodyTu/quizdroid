package edu.uw.ischool.ctu4.quizdroid

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Preferences : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val url = findViewById<EditText>(R.id.URL)
        val minutes = findViewById<EditText>(R.id.minutes)
        val set = findViewById<Button>(R.id.set_pref)
        val sp = getSharedPreferences("URLPrefs", Context.MODE_PRIVATE)

        set.setOnClickListener({
            val editor = sp.edit()
            editor.putString("URL", url.getText().toString())
            editor.putInt("Minutes", minutes.getText().toString().toInt())
            editor.commit()
        })
    }
}