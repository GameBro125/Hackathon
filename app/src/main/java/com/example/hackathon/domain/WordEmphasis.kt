package com.example.hackathon.domain

data class WordEmphasis(
    val word: String,
    val emphasisIndex: Int
)

val testData: List<WordEmphasis> = listOf(
    WordEmphasis("Кот", 2),
    WordEmphasis("Собака", 4),
    WordEmphasis("Дом", 1),
    WordEmphasis("Рыба", 3),
    // Добавьте больше слов с индексами ударений по мере необходимости
)