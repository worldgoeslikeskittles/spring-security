package com.example.springsecurityold.controller

import com.example.springsecurityold.dto.ClientRegistrationDto
import com.example.springsecurityold.dto.EmployeeRegistrationDto
import com.example.springsecurityold.service.RegistrationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/registration")
class RegistrationController(
    private val accountService: RegistrationService
) {
    @PostMapping("/client")
    fun registerClient(dto: ClientRegistrationDto) = accountService.registerClient(dto)

    @PostMapping("/manager")
    fun registerManager(dto: EmployeeRegistrationDto) {
        accountService.registerManager(dto)
    }

    @PostMapping("/doctor")
    fun registerDoctor(dto: EmployeeRegistrationDto) {
        accountService.registerDoctor(dto)
    }
}