package com.example.finalproject1_11.Model.DB

data class Distance(
    val destination_addresses: List<Any>,
    val error_message: String,
    val origin_addresses: List<Any>,
    val rows: List<Any>,
    val status: String
)