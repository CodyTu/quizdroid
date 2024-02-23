package edu.uw.ischool.ctu4.quizdroid

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class Preferences : AppCompatActivity() {
    var MESSAGE: String = "edu.uw.ischool.ctu4.quizapp"
    val TAG = "MyTag"
    lateinit var queue : RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val url = findViewById<EditText>(R.id.URL)
        val minutes = findViewById<EditText>(R.id.minutes)
        val set = findViewById<Button>(R.id.set_pref)
        val sp = getSharedPreferences("URLPrefs", Context.MODE_PRIVATE)

        queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url.getText().toString(),
            Response.Listener { response ->
                writeData(response)
            },
            Response.ErrorListener { error ->
                Log.d(MESSAGE, error.toString())
            })


        set.setOnClickListener({
            val editor = sp.edit()
            editor.putString("URL", url.getText().toString())
            editor.putInt("Minutes", minutes.getText().toString().toInt())
            editor.commit()

            stringRequest.tag = TAG
            queue.add(stringRequest)
        })
    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll(TAG)
    }

    fun writeData(data : String) {
        val file = File(this.getFilesDir(), "questions.json")
        val fileWriter = FileWriter(file)
        val buffer = BufferedWriter(fileWriter)
        buffer.write(data)
        buffer.close()
    }
}