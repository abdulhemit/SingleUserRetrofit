package com.example.singleuserretrofit.Model

data class User (
        val data: data,
        val support: support
)

data class data(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

data class support(
    val url: String,
    val text: String
)