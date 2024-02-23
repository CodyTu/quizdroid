package edu.uw.ischool.ctu4.quizdroid

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.getSystemService
import java.io.IOException
import java.nio.charset.Charset
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val math = findViewById<Button>(R.id.math)
        val physics = findViewById<Button>(R.id.physics)
        val marvel = findViewById<Button>(R.id.marvel)

        val mathDes = findViewById<TextView>(R.id.mathDes)
        val physDes = findViewById<TextView>(R.id.physDes)
        val marvelDes = findViewById<TextView>(R.id.marvelDes)

        val offline = findViewById<TextView>(R.id.offline)
        val airplane = findViewById<Button>(R.id.airplaneMode)

        val quiz = QuizApp()
        math.text = quiz.getTopics()[0].title
        physics.text = quiz.getTopics()[1].title
        marvel.text = quiz.getTopics()[2].title

        mathDes.text = quiz.getTopics()[0].short_description
        physDes.text = quiz.getTopics()[1].short_description
        marvelDes.text = quiz.getTopics()[2].short_description

        if (!isOnline(this) && Settings.System.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) == 0) {
            throw Exception("No internet access")
        }

        if (Settings.System.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) == 1) {
            offline.text = "You are currently offline"
            airplane.setVisibility(View.VISIBLE)

        } else {
            offline.text = ""
            airplane.setVisibility(View.GONE)
        }

        airplane.setOnClickListener({
            startActivityForResult(Intent(android.provider.Settings.ACTION_SETTINGS), 0)
        })

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater : MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.setting -> {
                val intent = Intent(this, Preferences::class.java)
                startActivity((intent))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}