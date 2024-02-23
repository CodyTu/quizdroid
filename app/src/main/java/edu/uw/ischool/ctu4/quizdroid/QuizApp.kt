package edu.uw.ischool.ctu4.quizdroid

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Message
import android.renderscript.ScriptGroup.Input
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
import java.nio.charset.Charset

class QuizApp : TopicRepository, android.app.Application() {
    var MESSAGE: String = "edu.uw.ischool.ctu4.quizapp"
    override fun onCreate() {
        super.onCreate()
        Log.i(MESSAGE, "QuizApp is loaded and running")
        val data = "[\n" +
                "  { \"title\": \"Math\",\n" +
                "    \"desc\": \"Math is an area of knowledge that includes the topics of numbers, formulas and related structures, shapes and the spaces in which they are contained, and quantities and their changes.\",\n" +
                "    \"questions\": [\n" +
                "      {\n" +
                "        \"text\": \"Find 5 + 7 + 9\",\n" +
                "        \"answer\": \"4\",\n" +
                "        \"answers\": [\n" +
                "          \"20\",\n" +
                "          \"12\",\n" +
                "          \"14\",\n" +
                "          \"21\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"text\": \"On dividing 426 by 4, we get the remainder as\",\n" +
                "        \"answer\": \"2\",\n" +
                "        \"answers\": [\n" +
                "          \"0\",\n" +
                "          \"2\",\n" +
                "          \"4\",\n" +
                "          \"6\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"text\": \"Simplify the expression: 15 + 10 ÷ 5 = ?\",\n" +
                "        \"answer\": \"1\",\n" +
                "        \"answers\": [\n" +
                "          \"17\",\n" +
                "          \"15\",\n" +
                "          \"5\",\n" +
                "          \"10\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"text\": \"What is the value of t, if m + 2 = n and n = 3?\",\n" +
                "        \"answer\": \"1\",\n" +
                "        \"answers\": [\n" +
                "          \"1\",\n" +
                "          \"2\",\n" +
                "          \"3\",\n" +
                "          \"4\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"text\": \"On converting 32 yards to foot, we get\",\n" +
                "        \"answer\": \"2\",\n" +
                "        \"answers\": [\n" +
                "          \"960 ft\",\n" +
                "          \"96 ft\",\n" +
                "          \"0.96 ft\",\n" +
                "          \"0.096 ft\"\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  { \"title\": \"Physics\",\n" +
                "    \"desc\": \"Physics is the branch of science that deals with the structure of matter and how the fundamental constituents of the universe interact.\",\n" +
                "    \"questions\": [\n" +
                "      {\n" +
                "        \"text\": \"The slope of the position time graph of an object moving with negative velocity is?\",\n" +
                "        \"answer\": \"2\",\n" +
                "        \"answers\": [\n" +
                "          \"Zero\",\n" +
                "          \"Negative\",\n" +
                "          \"Positive\",\n" +
                "          \"Inifity\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"text\": \"The SI unit of displacement is\",\n" +
                "        \"answer\": \"3\",\n" +
                "        \"answers\": [\n" +
                "          \"Kilometer\",\n" +
                "          \"Centimeter\",\n" +
                "          \"Meter\",\n" +
                "          \"Millimeter\"\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  { \"title\": \"Marvel\",\n" +
                "    \"desc\": \"Marvel is an American media and entertainment company regarded as one of the “big two” publishers in the comics industry.\",\n" +
                "    \"questions\": [\n" +
                "      {\n" +
                "        \"text\": \"Who is the strongest Avenger?\",\n" +
                "        \"answer\": \"3\",\n" +
                "        \"answers\": [\n" +
                "          \"Hulk\",\n" +
                "          \"Iron Man\",\n" +
                "          \"Thor\",\n" +
                "          \"Captain America\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"text\": \"What is Iron Man's real name?\",\n" +
                "        \"answer\": \"3\",\n" +
                "        \"answers\": [\n" +
                "          \"Bruce Banner\",\n" +
                "          \"Steve Rogers\",\n" +
                "          \"Tony Stark\",\n" +
                "          \"Peter Parker\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"text\": \"What is the name of Thor's hammer?\",\n" +
                "        \"answer\": \"1\",\n" +
                "        \"answers\": [\n" +
                "          \"Mjolnir\",\n" +
                "          \"Stormbreaker\",\n" +
                "          \"Gungnir\",\n" +
                "          \"Iron Hammer\"\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]"
        writeData(data)
    }

    fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
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