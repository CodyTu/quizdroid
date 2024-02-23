package edu.uw.ischool.ctu4.quizdroid

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Message
import android.renderscript.ScriptGroup.Input
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset


class QuizApp : TopicRepository, android.app.Application() {
    var MESSAGE: String = "edu.uw.ischool.ctu4.quizapp"
    override fun onCreate() {
        super.onCreate()
        Log.i(MESSAGE, "QuizApp is loaded and running")
        val sp = getApplicationContext().getSharedPreferences("URLPrefs", Context.MODE_PRIVATE)
        val url = sp.getString("URL", "https://tednewardsandbox.site44.com/questions.json")

        Toast.makeText(this, url, Toast.LENGTH_SHORT).show()

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                writeData(response)
            },
            Response.ErrorListener { Log.d(MESSAGE, "That didn't work") })

        queue.add(stringRequest)
    }

    fun writeData(data : String) {
        val file = File(this.getFilesDir(), "questions.json")
        val fileWriter = FileWriter(file)
        val buffer = BufferedWriter(fileWriter)
        buffer.write(data)
        buffer.close()
        Log.i(MESSAGE, getFilesDir().toString())
    }


    override fun getTopics(): List<TopicRepository.Topic> {
        val file = File("/data/user/0/edu.uw.ischool.ctu4.quizdroid/files", "questions.json")
        val fileReader = FileReader(file)
        val buffer = BufferedReader(fileReader)

        val stringBuilder = StringBuilder()
        var line = buffer.readLine()
        while (line != null) {
            stringBuilder.append(line).append("\n")
            line = buffer.readLine()
        }
        buffer.close()

        val response = stringBuilder.toString()
        val json = JSONArray(response)
        val topicList = arrayListOf<TopicRepository.Topic>()
        for (i in 0..json.length() - 1) {
            val topic = json.getJSONObject(i)
            val title = topic.get("title").toString()
            val desc = topic.get("desc").toString()

            val questions = arrayListOf<TopicRepository.Question>()
            val questionList = topic.getJSONArray("questions")
            for (j in 0..questionList.length() - 1) {
                val question = questionList.getJSONObject(j)
                val text = question.getString("text")
                val answers = arrayListOf<String>()
                val answerList = question.getJSONArray("answers")       // make a list

                for (k in 0..answerList.length() - 1) {
                    answers.add(answerList[k].toString())
                }

                val answer = question.getInt("answer")
                questions.add(TopicRepository.Question(text, answers, answer))
            }

            topicList.add(TopicRepository.Topic(title, desc, desc, questions))
        }
        return topicList
//        val input = readJsonFromAssets(this, "questions.json")
//
//        val topics = Gson().fromJson<List<TopicRepository.Topic>>(input, object: TypeToken<List<TopicRepository.Topic>>() {}.type)
//
//        return topics
//        return listOf(
//            TopicRepository.Topic(
//            "Math",
//            "Math is an area of knowledge that includes the topics of numbers, formulas and related structures, shapes and the spaces in which they are contained, and quantities and their changes.",
//            "Mathematics is the science and study of quality, structure, space, and change. Mathematicians seek out patterns, formulate new conjectures, and establish truth by rigorous deduction from appropriately chosen axioms and definitions.",
//            listOf(
//                TopicRepository.Question(
//                    "Find 5 + 7 + 9",
//                    listOf(
//                        "20",
//                        "12",
//                        "14",
//                        "21"
//                    ),
//                    4
//                ),
//                TopicRepository.Question(
//                    "On dividing 426 by 4, we get the remainder as",
//                    listOf(
//                        "0",
//                        "2",
//                        "4",
//                        "6"
//                    ),
//                    2
//                ),
//                TopicRepository.Question(
//                    "Simplify the expression: 15 + 10 ÷ 5 = ?",
//                    listOf(
//                        "17",
//                        "15",
//                        "5",
//                        "10"
//                    ),
//                    1
//                ),
//                TopicRepository.Question(
//                    "What is the value of t, if m + 2 = n and n = 3?",
//                    listOf(
//                        "1",
//                        "2",
//                        "3",
//                        "4"
//                    ),
//                    1
//                ),
//                TopicRepository.Question(
//                    "On converting 32 yards to foot, we get",
//                    listOf("960 ft",
//                    "96 ft",
//                    "0.96 ft",
//                    "0.096 ft"
//                    ),
//                    2
//                )
//            )),
//            TopicRepository.Topic(
//                "Physics",
//                "Physics is the branch of science that deals with the structure of matter and how the fundamental constituents of the universe interact.",
//                "Physics is the branch of science that deals with the structure of matter and how the fundamental constituents of the universe interact. It studies objects ranging from the very small using quantum mechanics to the entire universe using general relativity.",
//                listOf(
//                    TopicRepository.Question(
//                        "The slope of the position time graph of an object moving with negative velocity is?",
//                        listOf("Zero",
//                        "Negative",
//                        "Positive",
//                        "Inifity"),
//                        2
//                    ),
//                    TopicRepository.Question(
//                        "The SI unit of displacement is",
//                        listOf("Kilometer",
//                        "Centimeter",
//                        "Meter",
//                        "Millimeter"),
//                        3
//                    )
//                )
//            ),
//            TopicRepository.Topic(
//                "Marvel",
//                "Marvel is an American media and entertainment company regarded as one of the “big two” publishers in the comics industry.",
//                "Marvel Comics is an American media and entertainment company regarded as one of the “big two” publishers in the comics industry. Its parent company, Marvel Entertainment, is a wholly owned subsidiary of the Disney Company.",
//                listOf(
//                    TopicRepository.Question(
//                        "Who is the strongest Avenger?",
//                        listOf("Hulk",
//                        "Iron Man",
//                        "Thor",
//                        "Captain America"),
//                        3
//                    ),
//                    TopicRepository.Question(
//                        "What is Iron Man\\'s real name?",
//                        listOf("Bruce Banner",
//                        "Steve Rogers",
//                        "Tony Stark",
//                        "Peter Parker"),
//                        3
//                    ),
//                    TopicRepository.Question(
//                        "What is the name of Thor\\'s hammer?",
//                        listOf("Mjolnir",
//                        "Stormbreaker",
//                        "Gungnir",
//                        "Iron Hammer"),
//                        1
//                    )
//                )
//
//            )
//        )
    }

}