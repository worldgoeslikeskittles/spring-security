package com.example.springsecurityold.dto

import com.example.springsecurityold.domain.entity.Employee

data class DoctorDto(
    val fullName: String,
    val workHours: String,
    val speciality: String
) {
    constructor(employee: Employee) : this(
        fullName = employee.fullName,
        workHours=  employee.workHours,
        speciality = employee.speciality!!
    )
}
