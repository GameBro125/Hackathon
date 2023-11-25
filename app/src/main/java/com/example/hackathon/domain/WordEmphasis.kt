package com.example.hackathon.domain

data class WordEmphasis(
    val word: String,
    val emphasisIndex: Int
)

val testData: List<WordEmphasis> = listOf(
    WordEmphasis("туфля", 4),
    WordEmphasis("дефис", 3),
    WordEmphasis("диспансер", 7),
    WordEmphasis("досуг", 3),
    WordEmphasis("жалюзи", 5),
    WordEmphasis("еретик", 4),
    WordEmphasis("значимость", 2),
    WordEmphasis("иксы", 0),
    WordEmphasis("конусов", 1),
    WordEmphasis("краны", 2),
    WordEmphasis("недруг", 1),
    WordEmphasis("поручни", 1),
    WordEmphasis("свелка", 2),
    WordEmphasis("статуя", 2),
    WordEmphasis("эксперт", 4)
)
val resultData: List<WordEmphasis> = mutableListOf()