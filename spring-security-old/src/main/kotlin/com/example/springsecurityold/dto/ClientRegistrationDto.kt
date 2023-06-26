package com.example.springsecurityold.dto

data class ClientRegistrationDto(
    val firstName: String,
    val lastName: String,
    val phone: String,
    open val login: String,
    open val password: String
)
