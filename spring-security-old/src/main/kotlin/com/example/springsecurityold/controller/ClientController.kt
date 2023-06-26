package com.example.springsecurityold.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clients")
class ClientController {

    @GetMapping("/")
    fun getClient(): String = "Hello"
}