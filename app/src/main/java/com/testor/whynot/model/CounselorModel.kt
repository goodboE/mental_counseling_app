package com.testor.whynot.model

data class CounselorModel(
    val counselorId: String,
    val counselorName: String,
    var counselorIntroduction: String,
    var counselorProfit: String,
    var counselorHeart: String,
    var counselorHashTag: MutableList<String>,
    val counselorImageUrl: String
)
