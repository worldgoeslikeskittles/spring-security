package com.example.springsecurityold.controller

import com.example.springsecurityold.dto.ClientDto
import com.example.springsecurityold.service.ClientService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/clients")
class ClientController(
    private val clientService: ClientService
) {
    @GetMapping("/current")
    fun getClient(principal: Principal) = clientService.findCurrentClient(principal)

    @GetMapping("/all")
    fun getAllClients() = clientService.findAllClients()

    @PutMapping("/current")
    fun updateCurrentClient(principal: Principal, dto: ClientDto) = clientService.updateClientCard(principal, dto)
}