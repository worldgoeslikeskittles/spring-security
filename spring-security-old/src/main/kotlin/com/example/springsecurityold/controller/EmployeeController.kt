package com.example.springsecurityold.controller

import com.example.springsecurityold.domain.enumerated.EmployeeType
import com.example.springsecurityold.dto.DoctorDto
import com.example.springsecurityold.service.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeeController(
    private val employeeService: EmployeeService
) {
    @GetMapping("/doctors")
    fun getAllAvailableDoctors() = employeeService.getAllEmployeesByType(EmployeeType.DOCTOR).map { DoctorDto(it) }
}