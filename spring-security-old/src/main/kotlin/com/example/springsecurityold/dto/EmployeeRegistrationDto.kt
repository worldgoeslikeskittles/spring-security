package com.example.springsecurityold.dto

data class EmployeeRegistrationDto(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val login: String,
    val password: String,
    val workingHours: String,
    val isAdmin: Boolean
)
