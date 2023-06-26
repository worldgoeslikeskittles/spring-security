package com.example.springsecurityold.dto

data class DoctorRegistrationDto(
val firstName: String,
val lastName: String,
val phone: String,
val login: String,
val password: String,
val workingHours: String,
val speciality: String,
val isAdmin: Boolean
)

