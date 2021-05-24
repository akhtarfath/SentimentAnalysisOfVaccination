package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response

data class CovidStatisticResponse(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val longitude: Double,
    val latitude: Double,
    val like: Int,
    val image: String
)

