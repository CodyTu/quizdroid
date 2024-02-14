package edu.uw.ischool.ctu4.quizdroid

interface TopicRepository {

    fun getTopics(): List<Topic>

    data class Question (
        val question: String,
        val answers: List<String>,
        val correctAnswer: Int
    )

    data class Topic (
        val title: String,
        val short_description: String,
        val long_description: String,
        val questions: List<Question>
    )
}