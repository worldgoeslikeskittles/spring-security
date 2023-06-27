package com.example.springsecurityold.controller

import com.example.springsecurityold.dto.ClientRegistrationDto
import com.example.springsecurityold.dto.DoctorRegistrationDto
import com.example.springsecurityold.dto.EmployeeRegistrationDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/registration")
class RegistrationController {

    @PostMapping
    fun registerClient(dto: ClientRegistrationDto){

    }

    @PostMapping("/manager")
    fun registerManager(dto: EmployeeRegistrationDto) {

    }

    @PostMapping("/doctor")
    fun registerDoctor(dto: DoctorRegistrationDto) {

    }
}