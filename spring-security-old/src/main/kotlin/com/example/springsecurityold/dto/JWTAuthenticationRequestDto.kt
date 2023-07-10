package com.example.springsecurityold.dto

data class JWTAuthenticationRequestDto(
    val username: String,
    val password: String
)