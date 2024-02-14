package edu.uw.ischool.ctu4.quizdroid

import android.app.Application
import android.os.Bundle
import android.os.Message
import android.util.Log

class QuizApp : TopicRepository, android.app.Application() {
    var MESSAGE: String = "edu.uw.ischool.ctu4.quizapp"
    override fun onCreate() {
        super.onCreate()
        Log.i(MESSAGE, "QuizApp is loaded and running")
    }

    override fun getTopics(): List<TopicRepository.Topic> {
        return listOf(
            TopicRepository.Topic(
            "Math",
            "Math is an area of knowledge that includes the topics of numbers, formulas and related structures, shapes and the spaces in which they are contained, and quantities and their changes.",
            "Mathematics is the science and study of quality, structure, space, and change. Mathematicians seek out patterns, formulate new conjectures, and establish truth by rigorous deduction from appropriately chosen axioms and definitions.",
            listOf(
                TopicRepository.Question(
                    "Find 5 + 7 + 9",
                    listOf(
                        "20",
                        "12",
                        "14",
                        "21"
                    ),
                    4
                ),
                TopicRepository.Question(
                    "On dividing 426 by 4, we get the remainder as",
                    listOf(
                        "0",
                        "2",
                        "4",
                        "6"
                    ),
                    2
                ),
                TopicRepository.Question(
                    "Simplify the expression: 15 + 10 ÷ 5 = ?",
                    listOf(
                        "17",
                        "15",
                        "5",
                        "10"
                    ),
                    1
                ),
                TopicRepository.Question(
                    "What is the value of t, if m + 2 = n and n = 3?",
                    listOf(
                        "1",
                        "2",
                        "3",
                        "4"
                    ),
                    1
                ),
                TopicRepository.Question(
                    "On converting 32 yards to foot, we get",
                    listOf("960 ft",
                    "96 ft",
                    "0.96 ft",
                    "0.096 ft"
                    ),
                    2
                )
            )),
            TopicRepository.Topic(
                "Physics",
                "Physics is the branch of science that deals with the structure of matter and how the fundamental constituents of the universe interact.",
                "Physics is the branch of science that deals with the structure of matter and how the fundamental constituents of the universe interact. It studies objects ranging from the very small using quantum mechanics to the entire universe using general relativity.",
                listOf(
                    TopicRepository.Question(
                        "The slope of the position time graph of an object moving with negative velocity is?",
                        listOf("Zero",
                        "Negative",
                        "Positive",
                        "Inifity"),
                        2
                    ),
                    TopicRepository.Question(
                        "The SI unit of displacement is",
                        listOf("Kilometer",
                        "Centimeter",
                        "Meter",
                        "Millimeter"),
                        3
                    )
                )
            ),
            TopicRepository.Topic(
                "Marvel",
                "Marvel is an American media and entertainment company regarded as one of the “big two” publishers in the comics industry.",
                "Marvel Comics is an American media and entertainment company regarded as one of the “big two” publishers in the comics industry. Its parent company, Marvel Entertainment, is a wholly owned subsidiary of the Disney Company.",
                listOf(
                    TopicRepository.Question(
                        "Who is the strongest Avenger?",
                        listOf("Hulk",
                        "Iron Man",
                        "Thor",
                        "Captain America"),
                        3
                    ),
                    TopicRepository.Question(
                        "What is Iron Man\\'s real name?",
                        listOf("Bruce Banner",
                        "Steve Rogers",
                        "Tony Stark",
                        "Peter Parker"),
                        3
                    ),
                    TopicRepository.Question(
                        "What is the name of Thor\\'s hammer?",
                        listOf("Mjolnir",
                        "Stormbreaker",
                        "Gungnir",
                        "Iron Hammer"),
                        1
                    )
                )

            )
        )
    }

}